package com.gabriel.benini.divideaiapp.feature.expense.data.remote.api

import com.gabriel.benini.divideaiapp.feature.expense.data.remote.dto.request.CreateExpenseRequestDto
import com.gabriel.benini.divideaiapp.feature.expense.data.remote.dto.response.ExpenseResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ExpenseApi {

    @GET("/api/expenses/{expenseId}")
    suspend fun getExpenseById(
        @Path("expenseId") expenseId: Long
    ): ExpenseResponseDto

    @GET("/api/expenses")
    suspend fun getExpensesByGroupId(
        @Query("groupId") groupId: Long
    ): List<ExpenseResponseDto>

    @POST("/api/expenses")
    suspend fun createExpense(
        @Body request: CreateExpenseRequestDto
    ): ExpenseResponseDto
}