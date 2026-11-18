package com.gabriel.beini.divideaiapp.feature.user.domain.repository

import com.gabriel.beini.divideaiapp.feature.user.domain.model.User

interface UserRepository {

    suspend fun getUsers(): List<User>

    suspend fun getUserById(id: Long): User
}