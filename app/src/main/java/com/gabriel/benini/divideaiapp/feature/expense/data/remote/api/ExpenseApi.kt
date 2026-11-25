package com.gabriel.benini.divideaiapp.feature.expense.data.remote.api

import com.gabriel.benini.divideaiapp.feature.expense.data.remote.dto.request.CreateExpenseRequestDto
import com.gabriel.benini.divideaiapp.feature.expense.data.remote.dto.request.UpdateExpenseRequestDto
import com.gabriel.benini.divideaiapp.feature.expense.data.remote.dto.response.ExpenseResponseDto
import com.gabriel.benini.divideaiapp.feature.expense.data.remote.dto.response.ExpenseSummaryResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @GET("/api/expenses/summary")
    suspend fun getGroupExpensesSummary(
        @Query("groupId") groupId: Long
    ): ExpenseSummaryResponseDto

    @POST("/api/expenses")
    suspend fun createExpense(
        @Body request: CreateExpenseRequestDto
    ): ExpenseResponseDto

    @PUT("/api/expenses/{expenseId}")
    suspend fun updateExpense(
        @Path("expenseId") expenseId: Long,
        @Body request: UpdateExpenseRequestDto
    ): ExpenseResponseDto

    @DELETE("/api/expenses/{expenseId}")
    suspend fun deleteExpense(
        @Path("expenseId") expenseId: Long
    )
}