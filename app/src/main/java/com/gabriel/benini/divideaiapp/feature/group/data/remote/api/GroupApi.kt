package com.gabriel.benini.divideaiapp.feature.group.data.remote.api

import com.gabriel.benini.divideaiapp.feature.group.data.remote.dto.request.CreateGroupRequestDto
import com.gabriel.benini.divideaiapp.feature.group.data.remote.dto.request.UpdateGroupRequestDto
import com.gabriel.benini.divideaiapp.feature.group.data.remote.dto.response.GroupMemberResponseDto
import com.gabriel.benini.divideaiapp.feature.group.data.remote.dto.response.GroupResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface GroupApi {

    @GET("/api/groups/{groupId}")
    suspend fun getGroupById(
        @Path("groupId") id: Long
    ): GroupResponseDto

    @GET("/api/groups")
    suspend fun getGroupsByUserId(
        @Query("userId") userId: Long
    ): List<GroupResponseDto>

    @POST("/api/groups")
    suspend fun createGroup(
        @Body request: CreateGroupRequestDto
    ): GroupResponseDto

    @PUT("/api/groups/{groupId}")
    suspend fun updateGroup(
        @Path("groupId") groupId: Long,
        @Body request: UpdateGroupRequestDto
    ): GroupResponseDto

    @DELETE("/api/groups/{groupId}")
    suspend fun deleteGroup(@Path("groupId") groupId: Long)

    @GET("/api/groups/{groupId}/members")
    suspend fun getGroupMembers(@Path("groupId") groupId: Long): List<GroupMemberResponseDto>
}