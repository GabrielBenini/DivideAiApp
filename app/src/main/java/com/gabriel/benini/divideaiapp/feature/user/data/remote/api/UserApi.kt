package com.gabriel.benini.divideaiapp.feature.user.data.remote.api

import com.gabriel.benini.divideaiapp.feature.user.data.remote.dto.request.CreateUserRequestDto
import com.gabriel.benini.divideaiapp.feature.user.data.remote.dto.request.UpdateUserRequestDto
import com.gabriel.benini.divideaiapp.feature.user.data.remote.dto.response.UserResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApi {

    @GET("/api/users")
    suspend fun getUsers(): List<UserResponseDto>

    @GET("/api/users/{id}")
    suspend fun getUserById(
        @Path("id") id: Long
    ): UserResponseDto

    @POST("/api/users")
    suspend fun createUser(
        @Body request: CreateUserRequestDto
    ): UserResponseDto

    @PUT("/api/users/{id}")
    suspend fun updateUser(
        @Body request: UpdateUserRequestDto,
        @Path("id") id: Long
    ): UserResponseDto

    @DELETE("/api/users/{id}")
    suspend fun deleteUser(
        @Path("id") id: Long
    )
}