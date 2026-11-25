package com.gabriel.benini.divideaiapp.feature.user.presentation.viewmodel

import com.gabriel.benini.divideaiapp.feature.user.domain.model.User

object UserContract {

    data class State(
        val isLoading: Boolean = false,
        val users: List<User> = emptyList(),
        val selectedUser: User? = null,
        val errorMessage: String? = null
    )

    sealed interface Event {

        data object LoadUsers : Event

        data class LoadUserById(val id: Long) : Event

        data class CreateUser(val name: String) : Event

        data class UpdateUser(
            val id: Long,
            val newName: String
        ) : Event

        data class DeleteUser(val id: Long) : Event

        data object ClearError : Event
    }

    sealed interface Effect {

        data class ShowMessage(val message: String) : Effect
    }
}