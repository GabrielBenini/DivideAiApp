package com.gabriel.benini.divideaiapp.feature.expense.presentation.viewmodel

import com.gabriel.benini.divideaiapp.feature.expense.domain.model.Expense
import com.gabriel.benini.divideaiapp.feature.expense.domain.model.ExpenseSummary

object ExpenseContract {

    data class State(
        val isLoading: Boolean = false,
        val expenses: List<Expense> = emptyList(),
        val selectedExpense: Expense? = null,
        val expensesGroupId: Long? = null,
        val summary: ExpenseSummary? = null,
        val errorMessage: String? = null
    )

    sealed interface Event {

        data class LoadExpenseById(
            val expenseId: Long
        ) : Event

        data class LoadExpensesByGroupId(
            val groupId: Long
        ) : Event

        data class CreateExpense(
            val description: String,
            val amountInCents: Long,
            val paidByUserId: Long,
            val groupId: Long
        ) : Event

        data class UpdateExpense(
            val expenseId: Long,
            val description: String,
            val amountInCents: Long,
            val paidByUserId: Long
        ) : Event

        data class DeleteExpense(
            val expenseId: Long
        ) : Event

        data class LoadGroupExpensesSummary(
            val groupId: Long
        ) : Event

        data object ClearError : Event
    }

    sealed interface Effect {

        data class ShowMessage(
            val message: String
        ) : Effect
    }
}