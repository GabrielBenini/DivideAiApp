package com.gabriel.benini.divideaiapp.feature.expense.presentation.viewmodel

import com.gabriel.benini.divideaiapp.feature.expense.domain.model.Expense

sealed interface ExpenseUiState{

    data object Loading: ExpenseUiState
    data class Error(val message: String): ExpenseUiState
    data class Success(val expense: Expense): ExpenseUiState
}