package com.gabriel.beini.divideaiapp.feature.group.data.remote.api

import com.gabriel.beini.divideaiapp.feature.group.data.remote.dto.response.GroupResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GroupApi {

    @GET("/api/groups/{groupId}")
    suspend fun getGroupById(
        @Path("groupId") id: Long
    ): GroupResponseDto

    @GET("/api/groups")
    suspend fun getGroupsByUserId(@Query("userId") userId: Long): List<GroupResponseDto>
}