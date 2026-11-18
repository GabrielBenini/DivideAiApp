package com.gabriel.beini.divideaiapp.feature.user.presentation.viewmodel

import com.gabriel.beini.divideaiapp.feature.user.domain.model.User

sealed interface UserUiState{
    data object Loading: UserUiState
    data class Success(val users: List<User>): UserUiState
    data class Error(val message: String): UserUiState
    data class UserDetailSuccess(val user: User): UserUiState
}