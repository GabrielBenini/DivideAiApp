package com.gabriel.benini.divideaiapp.feature.group.presentation.viewmodel

import com.gabriel.benini.divideaiapp.feature.group.domain.model.Group

sealed interface GroupUiState{

    data object Loading: GroupUiState
    data class Success(val group: Group): GroupUiState
    data class Error(val message: String): GroupUiState
    data class GroupsListSuccess(val groups: List<Group>): GroupUiState
}