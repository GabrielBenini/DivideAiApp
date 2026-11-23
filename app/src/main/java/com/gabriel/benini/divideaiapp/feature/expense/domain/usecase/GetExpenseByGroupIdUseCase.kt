package com.gabriel.benini.divideaiapp.feature.expense.domain.usecase

import com.gabriel.benini.divideaiapp.feature.expense.domain.model.Expense
import com.gabriel.benini.divideaiapp.feature.expense.domain.repository.ExpenseRepository
import javax.inject.Inject

class GetExpenseByGroupIdUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository,
) {

    suspend operator fun invoke(groupId: Long): List<Expense>{
        return expenseRepository.getExpenseByGroupId(groupId)
    }
}