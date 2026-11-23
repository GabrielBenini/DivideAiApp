package com.gabriel.benini.divideaiapp.feature.expense.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.benini.divideaiapp.feature.expense.domain.usecase.GetExpenseByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val getExpenseByIdUseCase: GetExpenseByIdUseCase,
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
}