package com.gabriel.benini.divideaiapp.feature.expense.domain.usecase

import com.gabriel.benini.divideaiapp.feature.expense.domain.repository.ExpenseRepository
import javax.inject.Inject

class DeleteExpenseUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository,
) {

    suspend operator fun invoke(expenseId: Long){
        expenseRepository.deleteExpense(expenseId)
    }
}