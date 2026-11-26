package com.gabriel.benini.divideaiapp.core.session.domain.usecase

import com.gabriel.benini.divideaiapp.core.session.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {

    operator fun invoke(): Flow<Long?> {
        return sessionRepository.observeUserId()
    }
}