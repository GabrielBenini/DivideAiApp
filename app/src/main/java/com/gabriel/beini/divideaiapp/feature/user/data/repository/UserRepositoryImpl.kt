package com.gabriel.beini.divideaiapp.feature.user.data.repository

import com.gabriel.beini.divideaiapp.feature.user.data.mapper.toDomain
import com.gabriel.beini.divideaiapp.feature.user.data.remote.api.UserApi
import com.gabriel.beini.divideaiapp.feature.user.domain.model.User
import com.gabriel.beini.divideaiapp.feature.user.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
): UserRepository {


    override suspend fun getUsers(): List<User> {
        return userApi.getUsers().map{ userResponseDto ->
            userResponseDto.toDomain()
        }
    }
}