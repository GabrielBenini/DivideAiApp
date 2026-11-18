package com.gabriel.beini.divideaiapp.feature.user.data.remote.api

import com.gabriel.beini.divideaiapp.feature.user.data.remote.dto.UserResponseDto
import retrofit2.http.GET

interface UserApi {

    @GET("/api/users")
    suspend fun getUsers(): List<UserResponseDto>
}