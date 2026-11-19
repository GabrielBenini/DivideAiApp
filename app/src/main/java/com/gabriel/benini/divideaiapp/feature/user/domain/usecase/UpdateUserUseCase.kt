package com.gabriel.benini.divideaiapp.feature.user.domain.usecase

import com.gabriel.benini.divideaiapp.feature.user.domain.model.User
import com.gabriel.benini.divideaiapp.feature.user.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(id: Long, newName: String): User{
        return userRepository.updateUser(id, newName)
    }
}