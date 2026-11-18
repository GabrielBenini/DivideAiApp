package com.gabriel.beini.divideaiapp.feature.user.data.remote.api

import com.gabriel.beini.divideaiapp.feature.user.data.remote.dto.response.UserResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("/api/users")
    suspend fun getUsers(): List<UserResponseDto>

    @GET("/api/users/{id}")
    suspend fun getUserById(@Path("id") id: Long): UserResponseDto
}