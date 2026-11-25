package com.gabriel.benini.divideaiapp.feature.expense.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.benini.divideaiapp.feature.expense.domain.usecase.CreateExpenseUseCase
import com.gabriel.benini.divideaiapp.feature.expense.domain.usecase.GetExpenseByGroupIdUseCase
import com.gabriel.benini.divideaiapp.feature.expense.domain.usecase.GetExpenseByIdUseCase
import com.gabriel.benini.divideaiapp.feature.expense.domain.usecase.UpdateExpenseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val getExpenseByIdUseCase: GetExpenseByIdUseCase,
    private val getExpenseByGroupIdUseCase: GetExpenseByGroupIdUseCase,
    private val createExpenseUseCase: CreateExpenseUseCase,
    private val updateExpenseUseCase: UpdateExpenseUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow<ExpenseUiState>(ExpenseUiState.Loading)

    val uiState = _uiState.asStateFlow()

    fun getExpenseById(expenseId: Long){
        viewModelScope.launch {
            _uiState.value = ExpenseUiState.Loading

            try {
                val expense = getExpenseByIdUseCase(expenseId)
                _uiState.value = ExpenseUiState.Success(expense)
            }catch (e: CancellationException){
                throw e

            } catch (e: Exception){
                _uiState.value = ExpenseUiState.Error(
                    message = "Erro ao buscar expense pelo id ${e}"
                )
            }
        }
    }

    fun getExpenseByGroupId(groupId: Long){
        viewModelScope.launch {
            _uiState.value = ExpenseUiState.Loading

            try {
                val groupExpense = getExpenseByGroupIdUseCase(groupId)
                _uiState.value = ExpenseUiState.ExpenseListSuccess(groupExpense)
            }catch (e: CancellationException){
                throw e

            } catch (e: Exception){
                _uiState.value = ExpenseUiState.Error(
                    message = "Erro ao buscar expenses pelo group id ${e}"
                )
            }
        }
    }

    fun createExpense(
        description: String,
        amountInCents: Long,
        paidByUserId: Long,
        groupId: Long
    ){
        viewModelScope.launch {
            _uiState.value = ExpenseUiState.Loading

            try {
                val createdExpense = createExpenseUseCase(
                    description = description,
                    amountInCents = amountInCents,
                    paidByUserId = paidByUserId,
                    groupId = groupId
                )
                _uiState.value = ExpenseUiState.Success(createdExpense)
            } catch (e: CancellationException){
                throw e

            } catch (e: Exception){
                _uiState.value = ExpenseUiState.Error(
                    message = "Erro ao criar expense ${e}"
                )
            }
        }
    }

    fun updateExpense(
        expenseId: Long,
        description: String,
        amountInCents: Long,
        paidByUserId: Long
    ){
        viewModelScope.launch {

            _uiState.value = ExpenseUiState.Loading

            try {
                val updatedExpense = updateExpenseUseCase(
                    expenseId,
                    description,
                    amountInCents,
                    paidByUserId
                )
                _uiState.value = ExpenseUiState.Success(updatedExpense)
            } catch (e: CancellationException){
                throw e

            } catch (e: Exception){
                _uiState.value = ExpenseUiState.Error(
                    message = "Erro ao atualizar expense ${e}"
                )
            }
        }
    }
}