package com.gabriel.benini.divideaiapp.feature.user.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.benini.divideaiapp.feature.user.domain.usecase.CreateUserUseCase
import com.gabriel.benini.divideaiapp.feature.user.domain.usecase.DeleteUserUseCase
import com.gabriel.benini.divideaiapp.feature.user.domain.usecase.GetUserByIdUseCase
import com.gabriel.benini.divideaiapp.feature.user.domain.usecase.GetUsersUseCase
import com.gabriel.benini.divideaiapp.feature.user.domain.usecase.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
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

    fun loadUserById(id: Long){
        viewModelScope.launch {
            _uiState.value = UserUiState.Loading

            try {
                val user = getUserByIdUseCase(id)

                _uiState.value = UserUiState.UserDetailSuccess(user)
            } catch (e: CancellationException){
                throw e

            } catch (e: Exception) {
                _uiState.value = UserUiState.Error(
                    message = e.message ?: "Erro ao buscar usuario"
                )
            }
        }
    }

    fun createUser(name: String){
        viewModelScope.launch {
            _uiState.value = UserUiState.Loading

            try {
                val createdUser = createUserUseCase(name)

                _uiState.value = UserUiState.UserDetailSuccess(createdUser)
            } catch (e: CancellationException){
                throw e

            } catch (e: Exception) {
                _uiState.value = UserUiState.Error(
                    message = e.message ?: "Erro ao criar usuario"
                )
            }
        }
    }

    fun updateUser(id: Long, newName: String){
        viewModelScope.launch {
            _uiState.value = UserUiState.Loading

            try {
                val updatedUser = updateUserUseCase(id, newName)

                _uiState.value = UserUiState.UserDetailSuccess(updatedUser)
            } catch (e: CancellationException){
                throw e

            } catch (e: Exception) {
                _uiState.value = UserUiState.Error(
                    message = e.message ?: "Erro ao atualizar usuario"
                )
            }
        }
    }

    fun deleteUser(id: Long){
        viewModelScope.launch {
            _uiState.value = UserUiState.Loading

            try {
                deleteUserUseCase(id)

                _uiState.value = UserUiState.DeletedUserSuccess
            } catch (e: CancellationException){
                throw e

            } catch (e: Exception){
                _uiState.value = UserUiState.Error(
                    message = e.message ?: "Erro ao excluir usuario"
                )
            }
        }
    }
}