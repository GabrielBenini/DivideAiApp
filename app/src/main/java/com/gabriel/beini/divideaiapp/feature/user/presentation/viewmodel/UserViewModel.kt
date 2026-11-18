package com.gabriel.beini.divideaiapp.feature.user.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.beini.divideaiapp.feature.user.domain.model.User
import com.gabriel.beini.divideaiapp.feature.user.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserUiState>(
        UserUiState.Loading
    )

    val uiState = _uiState.asStateFlow()

    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = UserUiState.Loading

            try {
                val users = getUsersUseCase()

                _uiState.value = UserUiState.Success(users)

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                _uiState.value = UserUiState.Error(
                    message = e.message ?: "Erro ao carregar usuários"
                )
            }
        }
    }
}