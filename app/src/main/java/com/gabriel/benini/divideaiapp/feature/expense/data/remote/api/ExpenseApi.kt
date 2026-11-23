package com.gabriel.benini.divideaiapp.feature.expense.data.remote.api

import com.gabriel.benini.divideaiapp.feature.expense.data.remote.dto.response.ExpenseResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ExpenseApi {

    @GET("/api/expenses/{expenseId}")
    suspend fun getExpenseById(
        @Path("expenseId") expenseId: Long
    ): ExpenseResponseDto
}