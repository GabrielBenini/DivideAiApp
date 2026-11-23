package com.gabriel.benini.divideaiapp.feature.expense.data.mapper

import com.gabriel.benini.divideaiapp.feature.expense.data.remote.dto.response.ExpenseResponseDto
import com.gabriel.benini.divideaiapp.feature.expense.domain.model.Expense
import java.time.LocalDateTime

fun ExpenseResponseDto.toDomain(): Expense{
    return Expense(
        id = id,
        description = description,
        amountInCents = amountInCents,
        paidByUserName = paidByUserName,
        groupId = groupId,
        createdAt = LocalDateTime.parse(createdAt)
    )
}