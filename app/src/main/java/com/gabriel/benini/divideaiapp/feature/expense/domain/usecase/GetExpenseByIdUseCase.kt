package com.gabriel.benini.divideaiapp.feature.expense.domain.usecase

import com.gabriel.benini.divideaiapp.feature.expense.domain.model.Expense
import com.gabriel.benini.divideaiapp.feature.expense.domain.repository.ExpenseRepository
import javax.inject.Inject

class GetExpenseByIdUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository
) {

    suspend operator fun invoke(expenseId: Long): Expense{
        return expenseRepository.getExpenseById(expenseId)
    }
}