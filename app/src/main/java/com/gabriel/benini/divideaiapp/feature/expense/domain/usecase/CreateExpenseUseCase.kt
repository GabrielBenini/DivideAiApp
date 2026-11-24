package com.gabriel.benini.divideaiapp.feature.expense.domain.usecase

import com.gabriel.benini.divideaiapp.feature.expense.domain.model.Expense
import com.gabriel.benini.divideaiapp.feature.expense.domain.repository.ExpenseRepository
import javax.inject.Inject

class CreateExpenseUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository,
) {

    suspend operator fun invoke(
        description: String,
        amountInCents: Long,
        paidByUserId: Long,
        groupId: Long
    ): Expense{
        return expenseRepository.createExpense(
            description = description,
            amountInCents = amountInCents,
            paidByUserId = paidByUserId,
            groupId = groupId
        )
    }
}