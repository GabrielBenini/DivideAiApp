package com.gabriel.benini.divideaiapp.core.session.domain.repository

import kotlinx.coroutines.flow.Flow

interface SessionRepository {

    suspend fun saveUserId(userId: Long)

    fun observeUserId(): Flow<Long?>

    suspend fun clearSession()
}