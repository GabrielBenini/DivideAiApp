package com.gabriel.benini.divideaiapp.feature.expense.data.mapper

import com.gabriel.benini.divideaiapp.feature.expense.data.remote.dto.response.ExpenseSummaryResponseDto
import com.gabriel.benini.divideaiapp.feature.expense.domain.model.ExpenseSummary

fun ExpenseSummaryResponseDto.toDomain(): ExpenseSummary{
    return ExpenseSummary(
        groupId = groupId,
        totalAmountInCents = totalAmountInCents,
        expenseCount = expenseCount
    )
}