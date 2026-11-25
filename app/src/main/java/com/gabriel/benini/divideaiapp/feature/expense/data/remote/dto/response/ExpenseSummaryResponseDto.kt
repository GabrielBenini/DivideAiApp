package com.gabriel.benini.divideaiapp.feature.expense.data.remote.dto.response

data class ExpenseSummaryResponseDto(
    val groupId: Long,
    val totalAmountInCents: Long,
    val expenseCount: Long
)
