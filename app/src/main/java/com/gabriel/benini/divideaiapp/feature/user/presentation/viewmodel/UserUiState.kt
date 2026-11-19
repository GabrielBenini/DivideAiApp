package com.gabriel.benini.divideaiapp.feature.user.presentation.viewmodel

import com.gabriel.benini.divideaiapp.feature.user.domain.model.User

sealed interface UserUiState{
    data object Loading: UserUiState
    data class Success(val users: List<User>): UserUiState
    data class Error(val message: String): UserUiState
    data class UserDetailSuccess(val user: User): UserUiState
    data object DeletedUserSuccess: UserUiState
}