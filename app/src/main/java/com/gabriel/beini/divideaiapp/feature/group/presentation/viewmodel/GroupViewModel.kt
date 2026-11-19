package com.gabriel.beini.divideaiapp.feature.group.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.beini.divideaiapp.feature.group.domain.usecase.CreateGroupUseCase
import com.gabriel.beini.divideaiapp.feature.group.domain.usecase.GetGroupByIdUseCase
import com.gabriel.beini.divideaiapp.feature.group.domain.usecase.GetGroupsByUserIdUseCase
import com.gabriel.beini.divideaiapp.feature.group.domain.usecase.UpdateGroupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val getGroupByIdUseCase: GetGroupByIdUseCase,
    private val getGroupsByUserIdUseCase: GetGroupsByUserIdUseCase,
    private val createGroupUseCase: CreateGroupUseCase,
    private val updateGroupUseCase: UpdateGroupUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow<GroupUiState>(GroupUiState.Loading)

    val uiState = _uiState.asStateFlow()

    fun loadGroupById(id: Long) {
        viewModelScope.launch {
            _uiState.value = GroupUiState.Loading

            try {

                val group = getGroupByIdUseCase(id)
                _uiState.value = GroupUiState.Success(group)
            } catch (e: CancellationException){
                throw e

            } catch (e: Exception){
                _uiState.value = GroupUiState.Error(
                    message = e.message ?: "Erro ao buscar grupo"
                )
            }
        }
    }

    fun loadGroupsByUserId(userId: Long){
        viewModelScope.launch {
            _uiState.value = GroupUiState.Loading

            try {

                val groups = getGroupsByUserIdUseCase(userId)
                _uiState.value = GroupUiState.GroupsListSuccess(groups)
            } catch (e: CancellationException){
                throw e

            } catch (e: Exception){
                _uiState.value = GroupUiState.Error(
                    message = e.message ?: "Erro ao buscar grupos pelo userId"
                )
            }
        }
    }

    fun createGroup(name: String, createdByUserId: Long) {
        viewModelScope.launch {
            _uiState.value = GroupUiState.Loading

            try {
                val createdGroup = createGroupUseCase(name, createdByUserId)
                _uiState.value = GroupUiState.Success(createdGroup)

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                _uiState.value = GroupUiState.Error(
                    message = e.message ?: "Erro ao criar grupo"
                )
            }
        }
    }

    fun updateGroup(groupId: Long, name: String){
        viewModelScope.launch {
            _uiState.value = GroupUiState.Loading

            try {
                val updatedGroup = updateGroupUseCase(groupId, name)
                _uiState.value = GroupUiState.Success(updatedGroup)
            } catch (e: CancellationException){
                throw e

            } catch (e: Exception){
                _uiState.value = GroupUiState.Error(
                    message = e.message ?: "Erro ao atualizar grupo"
                )
            }
        }
    }
}