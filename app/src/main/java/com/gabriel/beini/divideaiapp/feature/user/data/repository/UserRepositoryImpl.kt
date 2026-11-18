package com.gabriel.beini.divideaiapp.feature.user.data.repository

import com.gabriel.beini.divideaiapp.feature.user.data.mapper.toDomain
import com.gabriel.beini.divideaiapp.feature.user.data.remote.api.UserApi
import com.gabriel.beini.divideaiapp.feature.user.data.remote.dto.request.CreateUserRequestDto
import com.gabriel.beini.divideaiapp.feature.user.data.remote.dto.request.UpdateUserRequestDto
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

    override suspend fun getUserById(id: Long): User {
        return userApi.getUserById(id).toDomain()
    }

    override suspend fun createUser(name: String): User {
        return userApi.createUser(CreateUserRequestDto(name)).toDomain()
    }

    override suspend fun updateUser(id: Long, newName: String): User {
        return userApi.updateUser(UpdateUserRequestDto(newName), id).toDomain()
    }

    override suspend fun deleteUser(id: Long) {
        return userApi.deleteUser(id)
    }
}