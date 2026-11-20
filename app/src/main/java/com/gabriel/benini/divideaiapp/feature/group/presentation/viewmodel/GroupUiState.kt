package com.gabriel.benini.divideaiapp.feature.group.presentation.viewmodel

import com.gabriel.benini.divideaiapp.feature.group.domain.model.Group
import com.gabriel.benini.divideaiapp.feature.group.domain.model.GroupMember

sealed interface GroupUiState{

    data object Loading: GroupUiState
    data object DeletedGroupSuccess: GroupUiState
    data class Success(val group: Group): GroupUiState
    data class Error(val message: String): GroupUiState
    data class GroupsListSuccess(val groups: List<Group>): GroupUiState
    data class GroupMembersSuccess(val members: List<GroupMember>): GroupUiState
    data class GroupMemberSuccess(val member: GroupMember): GroupUiState
}