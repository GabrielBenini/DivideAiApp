package com.gabriel.benini.divideaiapp.feature.user.domain.usecase

import com.gabriel.benini.divideaiapp.feature.user.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(id: Long){
        return userRepository.deleteUser(id)
    }
}