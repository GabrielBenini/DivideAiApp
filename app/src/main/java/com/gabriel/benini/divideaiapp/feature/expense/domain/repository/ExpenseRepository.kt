    package com.gabriel.benini.divideaiapp.feature.expense.domain.repository

    import com.gabriel.benini.divideaiapp.feature.expense.domain.model.Expense
    import com.gabriel.benini.divideaiapp.feature.expense.domain.model.ExpenseSummary

    interface ExpenseRepository {

        suspend fun getExpenseById(
            expenseId: Long
        ): Expense

        suspend fun getExpenseByGroupId(
            groupId: Long
        ): List<Expense>

        suspend fun getGroupExpensesSummary(
            groupId: Long
        ): ExpenseSummary

        suspend fun createExpense(
            description: String,
            amountInCents: Long,
            paidByUserId: Long,
            groupId: Long
        ): Expense

        suspend fun updateExpense(
            expenseId: Long,
            description: String,
            amountInCents: Long,
            paidByUserId: Long
        ): Expense

        suspend fun deleteExpense(
            expenseId: Long
        )
    }