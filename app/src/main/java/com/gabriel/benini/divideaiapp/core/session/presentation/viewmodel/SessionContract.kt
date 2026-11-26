package com.gabriel.benini.divideaiapp.core.session.presentation.viewmodel

object SessionContract {

    data class State(
        val isLoading: Boolean = true,
        val userId: Long? = null,
        val errorMessage: String? = null
    )
}