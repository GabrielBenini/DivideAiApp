package com.gabriel.benini.divideaiapp.feature.expense.domain.model

data class ExpenseSummary(
    val groupId: Long,
    val totalAmountInCents: Long,
    val expenseCount: Long
)
