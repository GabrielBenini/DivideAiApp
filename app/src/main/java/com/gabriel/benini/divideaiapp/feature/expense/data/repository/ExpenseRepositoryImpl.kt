package com.gabriel.benini.divideaiapp.feature.expense.data.repository

import com.gabriel.benini.divideaiapp.feature.expense.data.mapper.toDomain
import com.gabriel.benini.divideaiapp.feature.expense.data.remote.api.ExpenseApi
import com.gabriel.benini.divideaiapp.feature.expense.domain.model.Expense
import com.gabriel.benini.divideaiapp.feature.expense.domain.repository.ExpenseRepository
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val expenseApi: ExpenseApi
): ExpenseRepository {

    override suspend fun getExpenseById(expenseId: Long): Expense{
        return expenseApi.getExpenseById(expenseId).toDomain()
    }

    override suspend fun getExpenseByGroupId(groupId: Long): List<Expense> {
        return expenseApi.getExpensesByGroupId(groupId).map { expense ->
            expense.toDomain()
        }
    }
}