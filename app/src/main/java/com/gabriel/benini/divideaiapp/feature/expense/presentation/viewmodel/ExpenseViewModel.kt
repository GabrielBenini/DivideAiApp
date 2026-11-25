package com.gabriel.benini.divideaiapp.feature.expense.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.benini.divideaiapp.feature.expense.domain.usecase.CreateExpenseUseCase
import com.gabriel.benini.divideaiapp.feature.expense.domain.usecase.DeleteExpenseUseCase
import com.gabriel.benini.divideaiapp.feature.expense.domain.usecase.GetExpenseByGroupIdUseCase
import com.gabriel.benini.divideaiapp.feature.expense.domain.usecase.GetExpenseByIdUseCase
import com.gabriel.benini.divideaiapp.feature.expense.domain.usecase.GetGroupExpensesSummaryUseCase
import com.gabriel.benini.divideaiapp.feature.expense.domain.usecase.UpdateExpenseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val getExpenseByIdUseCase: GetExpenseByIdUseCase,
    private val getExpenseByGroupIdUseCase: GetExpenseByGroupIdUseCase,
    private val createExpenseUseCase: CreateExpenseUseCase,
    private val updateExpenseUseCase: UpdateExpenseUseCase,
    private val deleteExpenseUseCase: DeleteExpenseUseCase,
    private val getGroupExpensesSummaryUseCase: GetGroupExpensesSummaryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExpenseContract.State())
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<ExpenseContract.Effect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: ExpenseContract.Event) {
        when (event) {

            is ExpenseContract.Event.LoadExpenseById ->
                loadExpenseById(event.expenseId)

            is ExpenseContract.Event.LoadExpensesByGroupId ->
                loadExpensesByGroupId(event.groupId)

            is ExpenseContract.Event.CreateExpense ->
                createExpense(
                    description = event.description,
                    amountInCents = event.amountInCents,
                    paidByUserId = event.paidByUserId,
                    groupId = event.groupId
                )

            is ExpenseContract.Event.UpdateExpense ->
                updateExpense(
                    expenseId = event.expenseId,
                    description = event.description,
                    amountInCents = event.amountInCents,
                    paidByUserId = event.paidByUserId
                )

            is ExpenseContract.Event.DeleteExpense ->
                deleteExpense(event.expenseId)

            is ExpenseContract.Event.LoadGroupExpensesSummary ->
                loadGroupExpensesSummary(event.groupId)

            ExpenseContract.Event.ClearError ->
                clearError()
        }
    }

    private fun loadExpenseById(expenseId: Long) {
        viewModelScope.launch {
            startLoading()

            try {
                val expense = getExpenseByIdUseCase(expenseId)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        selectedExpense = expense
                    )
                }

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao buscar despesa")
            }
        }
    }

    private fun loadExpensesByGroupId(groupId: Long) {
        viewModelScope.launch {
            startLoading()

            try {
                val expenses = getExpenseByGroupIdUseCase(groupId)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        expenses = expenses,
                        expensesGroupId = groupId
                    )
                }

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao buscar despesas do grupo")
            }
        }
    }

    private fun createExpense(
        description: String,
        amountInCents: Long,
        paidByUserId: Long,
        groupId: Long
    ) {
        viewModelScope.launch {
            startLoading()

            try {
                val createdExpense = createExpenseUseCase(
                    description = description,
                    amountInCents = amountInCents,
                    paidByUserId = paidByUserId,
                    groupId = groupId
                )

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        selectedExpense = createdExpense,

                        expenses = if (state.expensesGroupId == groupId) {
                            state.expenses + createdExpense
                        } else {
                            state.expenses
                        },

                        summary = state.summary
                            ?.takeUnless { it.groupId == groupId }
                    )
                }

                _effect.emit(
                    ExpenseContract.Effect.ShowMessage(
                        "Despesa criada com sucesso"
                    )
                )

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao criar despesa")
            }
        }
    }

    private fun updateExpense(
        expenseId: Long,
        description: String,
        amountInCents: Long,
        paidByUserId: Long
    ) {
        viewModelScope.launch {
            startLoading()

            try {
                val updatedExpense = updateExpenseUseCase(
                    expenseId,
                    description,
                    amountInCents,
                    paidByUserId
                )

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        selectedExpense = updatedExpense,

                        expenses = state.expenses.map { expense ->
                            if (expense.id == expenseId) {
                                updatedExpense
                            } else {
                                expense
                            }
                        },

                        summary = state.summary
                            ?.takeUnless {
                                it.groupId == updatedExpense.groupId
                            }
                    )
                }

                _effect.emit(
                    ExpenseContract.Effect.ShowMessage(
                        "Despesa atualizada com sucesso"
                    )
                )

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao atualizar despesa")
            }
        }
    }

    private fun deleteExpense(expenseId: Long) {
        viewModelScope.launch {
            startLoading()

            try {
                val expenseGroupId = _uiState.value.let { state ->
                    state.expenses
                        .find { it.id == expenseId }
                        ?.groupId
                        ?: state.selectedExpense
                            ?.takeIf { it.id == expenseId }
                            ?.groupId
                }

                deleteExpenseUseCase(expenseId)

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,

                        expenses = state.expenses.filterNot {
                            it.id == expenseId
                        },

                        selectedExpense = state.selectedExpense
                            ?.takeUnless { it.id == expenseId },

                        summary = state.summary?.takeUnless {
                            expenseGroupId == null ||
                                    it.groupId == expenseGroupId
                        }
                    )
                }

                _effect.emit(
                    ExpenseContract.Effect.ShowMessage(
                        "Despesa excluída com sucesso"
                    )
                )

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao excluir despesa")
            }
        }
    }

    private fun loadGroupExpensesSummary(groupId: Long) {
        viewModelScope.launch {
            startLoading()

            try {
                val summary = getGroupExpensesSummaryUseCase(groupId)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        summary = summary
                    )
                }

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao buscar resumo das despesas")
            }
        }
    }

    private fun startLoading() {
        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }
    }

    private fun handleError(
        exception: Exception,
        defaultMessage: String
    ) {
        _uiState.update {
            it.copy(
                isLoading = false,
                errorMessage = exception.message ?: defaultMessage
            )
        }
    }

    private fun clearError() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }
}