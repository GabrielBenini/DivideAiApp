package com.gabriel.beini.divideaiapp.feature.user.domain.repository

import com.gabriel.beini.divideaiapp.feature.user.domain.model.User

interface UserRepository {

    suspend fun getUsers(): List<User>

    suspend fun getUserById(id: Long): User

    suspend fun createUser(name: String): User

    suspend fun updateUser(id: Long, newName: String): User

    suspend fun deleteUser(id: Long)
}