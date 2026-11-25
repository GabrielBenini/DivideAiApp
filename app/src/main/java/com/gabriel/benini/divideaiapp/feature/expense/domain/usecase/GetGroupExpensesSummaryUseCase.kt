package com.gabriel.benini.divideaiapp.feature.expense.domain.usecase

import com.gabriel.benini.divideaiapp.feature.expense.domain.model.ExpenseSummary
import com.gabriel.benini.divideaiapp.feature.expense.domain.repository.ExpenseRepository
import javax.inject.Inject

class GetGroupExpensesSummaryUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository
) {

    suspend operator fun invoke(
        groupId: Long,
    ): ExpenseSummary {
        return expenseRepository.getGroupExpensesSummary(groupId)
    }
}