package com.gabriel.benini.divideaiapp.feature.group.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.benini.divideaiapp.feature.group.domain.usecase.AddGroupMemberUseCase
import com.gabriel.benini.divideaiapp.feature.group.domain.usecase.CreateGroupUseCase
import com.gabriel.benini.divideaiapp.feature.group.domain.usecase.DeleteGroupMemberUseCase
import com.gabriel.benini.divideaiapp.feature.group.domain.usecase.DeleteGroupUseCase
import com.gabriel.benini.divideaiapp.feature.group.domain.usecase.GetGroupByIdUseCase
import com.gabriel.benini.divideaiapp.feature.group.domain.usecase.GetGroupMembersUseCase
import com.gabriel.benini.divideaiapp.feature.group.domain.usecase.GetGroupsByUserIdUseCase
import com.gabriel.benini.divideaiapp.feature.group.domain.usecase.UpdateGroupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val getGroupByIdUseCase: GetGroupByIdUseCase,
    private val getGroupsByUserIdUseCase: GetGroupsByUserIdUseCase,
    private val createGroupUseCase: CreateGroupUseCase,
    private val updateGroupUseCase: UpdateGroupUseCase,
    private val deleteGroupUseCase: DeleteGroupUseCase,
    private val getGroupMembersUseCase: GetGroupMembersUseCase,
    private val addGroupMemberUseCase: AddGroupMemberUseCase,
    private val deleteGroupMemberUseCase: DeleteGroupMemberUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GroupContract.State())
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<GroupContract.Effect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: GroupContract.Event) {
        when (event) {
            is GroupContract.Event.LoadGroupById ->
                loadGroupById(event.groupId)

            is GroupContract.Event.LoadGroupsByUserId ->
                loadGroupsByUserId(event.userId)

            is GroupContract.Event.CreateGroup ->
                createGroup(event.name, event.createdByUserId)

            is GroupContract.Event.UpdateGroup ->
                updateGroup(event.groupId, event.name)

            is GroupContract.Event.DeleteGroup ->
                deleteGroup(event.groupId)

            is GroupContract.Event.LoadGroupMembers ->
                loadGroupMembers(event.groupId)

            is GroupContract.Event.AddGroupMember ->
                addGroupMember(event.groupId, event.userId)

            is GroupContract.Event.DeleteGroupMember ->
                deleteGroupMember(event.groupId, event.userId)

            GroupContract.Event.ClearError ->
                clearError()
        }
    }

    private fun loadGroupById(groupId: Long) {
        viewModelScope.launch {
            startLoading()

            try {
                val group = getGroupByIdUseCase(groupId)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        selectedGroup = group
                    )
                }

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao buscar grupo")
            }
        }
    }

    private fun loadGroupsByUserId(userId: Long) {
        viewModelScope.launch {
            startLoading()

            try {
                val groups = getGroupsByUserIdUseCase(userId)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        groups = groups
                    )
                }

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao buscar grupos do usuário")
            }
        }
    }

    private fun createGroup(
        name: String,
        createdByUserId: Long
    ) {
        viewModelScope.launch {
            startLoading()

            try {
                val createdGroup = createGroupUseCase(
                    name,
                    createdByUserId
                )

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        selectedGroup = createdGroup,
                        groups = it.groups + createdGroup
                    )
                }

                _effect.emit(
                    GroupContract.Effect.ShowMessage(
                        "Grupo criado com sucesso"
                    )
                )

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao criar grupo")
            }
        }
    }

    private fun updateGroup(
        groupId: Long,
        name: String
    ) {
        viewModelScope.launch {
            startLoading()

            try {
                val updatedGroup = updateGroupUseCase(
                    groupId,
                    name
                )

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        selectedGroup = updatedGroup,
                        groups = state.groups.map { group ->
                            if (group.id == groupId) {
                                updatedGroup
                            } else {
                                group
                            }
                        }
                    )
                }

                _effect.emit(
                    GroupContract.Effect.ShowMessage(
                        "Grupo atualizado com sucesso"
                    )
                )

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao atualizar grupo")
            }
        }
    }

    private fun deleteGroup(groupId: Long) {
        viewModelScope.launch {
            startLoading()

            try {
                deleteGroupUseCase(groupId)

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,

                        groups = state.groups.filterNot {
                            it.id == groupId
                        },

                        selectedGroup = state.selectedGroup
                            ?.takeUnless { it.id == groupId },

                        members = if (state.membersGroupId == groupId) {
                            emptyList()
                        } else {
                            state.members
                        },

                        membersGroupId = state.membersGroupId
                            ?.takeUnless { it == groupId }
                    )
                }

                _effect.emit(
                    GroupContract.Effect.ShowMessage(
                        "Grupo excluído com sucesso"
                    )
                )

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao excluir grupo")
            }
        }
    }

    private fun loadGroupMembers(groupId: Long) {
        viewModelScope.launch {
            startLoading()

            try {
                val members = getGroupMembersUseCase(groupId)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        members = members,
                        membersGroupId = groupId
                    )
                }

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao buscar membros do grupo")
            }
        }
    }

    private fun addGroupMember(
        groupId: Long,
        userId: Long
    ) {
        viewModelScope.launch {
            startLoading()

            try {
                val member = addGroupMemberUseCase(
                    groupId,
                    userId
                )

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,

                        members = if (state.membersGroupId == groupId) {
                            state.members.filterNot {
                                it.userId == member.userId
                            } + member
                        } else {
                            state.members
                        }
                    )
                }

                _effect.emit(
                    GroupContract.Effect.ShowMessage(
                        "${member.userName} adicionado com sucesso ao grupo"
                    )
                )

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao adicionar membro ao grupo")
            }
        }
    }

    private fun deleteGroupMember(
        groupId: Long,
        userId: Long
    ) {
        viewModelScope.launch {
            startLoading()

            try {
                deleteGroupMemberUseCase(
                    groupId,
                    userId
                )

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,

                        members = if (state.membersGroupId == groupId) {
                            state.members.filterNot {
                                it.userId == userId
                            }
                        } else {
                            state.members
                        }
                    )
                }

                _effect.emit(
                    GroupContract.Effect.ShowMessage(
                        "Membro excluído com sucesso do grupo"
                    )
                )

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(e, "Erro ao excluir membro do grupo")
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
}