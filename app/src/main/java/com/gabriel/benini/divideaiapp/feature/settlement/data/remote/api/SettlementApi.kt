package com.gabriel.benini.divideaiapp.feature.settlement.data.remote.api

import com.gabriel.benini.divideaiapp.feature.settlement.data.remote.dto.response.GroupBalanceResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface SettlementApi {

    @GET("/api/groups/{groupId}/balance")
    suspend fun getGroupBalance(
        @Path("groupId") groupId: Long
    ): GroupBalanceResponseDto
}