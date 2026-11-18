package com.gabriel.beini.divideaiapp.feature.group.data.remote.api

import com.gabriel.beini.divideaiapp.feature.group.data.remote.dto.response.GroupResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GroupApi {

    @GET("/api/groups/{groupId}")
    suspend fun getGroupById(
        @Path("groupId") id: Long
    ): GroupResponseDto
}