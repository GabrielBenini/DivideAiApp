package com.gabriel.benini.divideaiapp.core.session.domain.usecase

import com.gabriel.benini.divideaiapp.core.session.domain.repository.SessionRepository
import javax.inject.Inject

class ClearSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
){

    suspend operator fun invoke(){
        return sessionRepository.clearSession()
    }
}