    package com.gabriel.benini.divideaiapp.feature.expense.domain.repository

    import com.gabriel.benini.divideaiapp.feature.expense.domain.model.Expense

    interface ExpenseRepository {

        suspend fun getExpenseById(
            expenseId: Long
        ): Expense
    }