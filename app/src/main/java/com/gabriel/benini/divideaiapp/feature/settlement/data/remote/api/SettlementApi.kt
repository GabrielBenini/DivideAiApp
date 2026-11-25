package com.gabriel.benini.divideaiapp.feature.settlement.data.remote.api

import com.gabriel.benini.divideaiapp.feature.settlement.data.remote.dto.response.GroupBalanceResponseDto
import com.gabriel.benini.divideaiapp.feature.settlement.data.remote.dto.response.GroupSettlementResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface SettlementApi {

    @GET("/api/groups/{groupId}/balance")
    suspend fun getGroupBalance(
        @Path("groupId") groupId: Long
    ): GroupBalanceResponseDto

    @GET("/api/groups/{groupId}/settlement")
    suspend fun getGroupSettlement(
        @Path("groupId") groupId: Long
    ): GroupSettlementResponseDto
}