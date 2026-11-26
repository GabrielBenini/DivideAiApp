package com.gabriel.benini.divideaiapp.core.session.data.repository

import com.gabriel.benini.divideaiapp.core.datastore.SessionDataStore
import com.gabriel.benini.divideaiapp.core.session.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionDataStore: SessionDataStore
) : SessionRepository {
    override suspend fun saveUserId(userId: Long) {
        sessionDataStore.saveUserId(userId)
    }

    override fun observeUserId(): Flow<Long?> {
        return sessionDataStore.observeUserId()
    }

    override suspend fun clearSession() {
        sessionDataStore.clearSession()
    }


}