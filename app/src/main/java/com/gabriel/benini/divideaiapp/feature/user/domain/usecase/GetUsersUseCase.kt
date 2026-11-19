package com.gabriel.benini.divideaiapp.feature.user.domain.usecase

import com.gabriel.benini.divideaiapp.feature.user.domain.model.User
import com.gabriel.benini.divideaiapp.feature.user.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
){

    suspend operator fun invoke(): List<User>{
        return userRepository.getUsers()
    }
}