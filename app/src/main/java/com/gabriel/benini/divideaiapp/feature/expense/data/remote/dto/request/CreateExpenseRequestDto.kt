package com.gabriel.benini.divideaiapp.feature.expense.data.remote.dto.request

data class CreateExpenseRequestDto(
    val description: String,
    val amountInCents: Long,
    val paidByUserId: Long,
    val groupId: Long
)
