package com.gabriel.benini.divideaiapp.feature.expense.domain.model

import java.time.LocalDateTime

data class Expense(
    val id: Long,
    val description: String,
    val amountInCents: Long,
    val paidByUserName: String,
    val groupId: Long,
    val createdAt: LocalDateTime
)
