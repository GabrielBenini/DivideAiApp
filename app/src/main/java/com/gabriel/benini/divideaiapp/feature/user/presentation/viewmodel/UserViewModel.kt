package com.gabriel.benini.divideaiapp.feature.user.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.benini.divideaiapp.core.session.domain.usecase.ClearSessionUseCase
import com.gabriel.benini.divideaiapp.core.session.domain.usecase.ObserveSessionUseCase
import com.gabriel.benini.divideaiapp.core.session.domain.usecase.SaveSessionUseCase
import com.gabriel.benini.divideaiapp.feature.user.domain.usecase.CreateUserUseCase
import com.gabriel.benini.divideaiapp.feature.user.domain.usecase.DeleteUserUseCase
import com.gabriel.benini.divideaiapp.feature.user.domain.usecase.GetUserByIdUseCase
import com.gabriel.benini.divideaiapp.feature.user.domain.usecase.GetUsersUseCase
import com.gabriel.benini.divideaiapp.feature.user.domain.usecase.UpdateUserUseCase
import com.gabriel.benini.divideaiapp.feature.user.presentation.viewmodel.UserContract.Effect.ShowMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val saveSessionUseCase: SaveSessionUseCase,
    private val observeSessionUseCase: ObserveSessionUseCase,
    private val clearSessionUseCase: ClearSessionUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserContract.State())
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<UserContract.Effect>()
    val effect = _effect.asSharedFlow()

    init {
        observeSession()
    }

    fun onEvent(event: UserContract.Event) {
        when (event) {
            UserContract.Event.LoadUsers ->
                loadUsers()

            is UserContract.Event.LoadUserById ->
                loadUserById(event.id)

            is UserContract.Event.CreateUser ->
                createUser(event.name)

            is UserContract.Event.UpdateUser ->
                updateUser(event.id, event.newName)

            is UserContract.Event.DeleteUser ->
                deleteUser(event.id)

            is UserContract.Event.SelectUser -> {
                selectUser(event.userId)
            }

            UserContract.Event.Logout -> {
                logout()
            }

            UserContract.Event.ClearError ->
                clearError()
        }
    }

    private fun loadUsers() {
        viewModelScope.launch {
            startLoading()

            try {
                val users = getUsersUseCase()

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        users = users
                    )
                }

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao carregar usuários")
            }
        }
    }

    private fun loadUserById(id: Long) {
        viewModelScope.launch {
            startLoading()

            try {
                val user = getUserByIdUseCase(id)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        selectedUser = user
                    )
                }

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao buscar usuário")
            }
        }
    }

    private fun createUser(name: String) {
        viewModelScope.launch {
            startLoading()

            try {
                val createdUser = createUserUseCase(name)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        selectedUser = createdUser,
                        users = it.users + createdUser
                    )
                }

                _effect.emit(
                    ShowMessage(
                        "Usuário criado com sucesso"
                    )
                )

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao criar usuário")
            }
        }
    }

    private fun updateUser(id: Long, newName: String) {
        viewModelScope.launch {
            startLoading()

            try {
                val updatedUser = updateUserUseCase(id, newName)

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        selectedUser = updatedUser,
                        users = state.users.map { user ->
                            if (user.id == id) updatedUser else user
                        }
                    )
                }

                _effect.emit(
                    ShowMessage(
                        "Usuário atualizado com sucesso"
                    )
                )

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao atualizar usuário")
            }
        }
    }

    private fun deleteUser(id: Long) {
        viewModelScope.launch {
            startLoading()

            try {
                deleteUserUseCase(id)

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        users = state.users.filterNot { it.id == id },
                        selectedUser = state.selectedUser
                            ?.takeUnless { it.id == id }
                    )
                }

                _effect.emit(
                    ShowMessage(
                        "Usuário excluído com sucesso"
                    )
                )

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao excluir usuário")
            }
        }
    }

    private fun startLoading() {
        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }
    }

    private fun handleError(
        exception: Exception,
        defaultMessage: String
    ) {
        _uiState.update {
            it.copy(
                isLoading = false,
                errorMessage = exception.message ?: defaultMessage
            )
        }
    }

    private fun clearError() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }

    private fun observeSession() {
        viewModelScope.launch {
            try {
                observeSessionUseCase().collect { id ->
                    _uiState.update {
                        it.copy(
                            sessionUserId = id,
                            isSessionLoading = false
                        )
                    }
                }
            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isSessionLoading = false,
                        errorMessage = e.message ?: "Erro ao recuperar sessão"
                    )
                }
            }
        }
    }

    private fun selectUser(userId: Long) {
        viewModelScope.launch {
            try {
                saveSessionUseCase(userId)
                _effect.emit(
                    ShowMessage(
                        "Usuario selecionado com sucesso"
                    )
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        errorMessage = e.message ?: "Erro ao selecionar usuario"
                    )
                }
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            try {
                clearSessionUseCase()
                _effect.emit(
                    ShowMessage(
                        "Sessão encerrada com sucesso"
                    )
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        errorMessage = e.message ?: "Erro ao encerrar sessão"
                    )
                }
            }
        }
    }
}