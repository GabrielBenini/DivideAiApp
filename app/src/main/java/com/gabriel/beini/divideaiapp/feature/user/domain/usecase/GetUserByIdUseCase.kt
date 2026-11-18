package com.gabriel.beini.divideaiapp.feature.user.domain.usecase

import com.gabriel.beini.divideaiapp.feature.user.domain.model.User
import com.gabriel.beini.divideaiapp.feature.user.domain.repository.UserRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(id: Long): User{
        return userRepository.getUserById(id)
    }
}