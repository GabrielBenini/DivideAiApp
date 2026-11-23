package com.gabriel.benini.divideaiapp.feature.expense.data.remote.dto.response

data class ExpenseResponseDto(
    val id: Long,
    val description: String,
    val amountInCents: Long,
    val paidByUserName: String,
    val groupId: Long,
    val createdAt: String
)
