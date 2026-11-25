package com.gabriel.benini.divideaiapp.feature.group.presentation.viewmodel

import com.gabriel.benini.divideaiapp.feature.group.domain.model.Group
import com.gabriel.benini.divideaiapp.feature.group.domain.model.GroupMember

object GroupContract {

    data class State(
        val isLoading: Boolean = false,
        val groups: List<Group> = emptyList(),
        val selectedGroup: Group? = null,
        val members: List<GroupMember> = emptyList(),
        val membersGroupId: Long? = null,
        val errorMessage: String? = null
    )

    sealed interface Event {

        data class LoadGroupById(
            val groupId: Long
        ) : Event

        data class LoadGroupsByUserId(
            val userId: Long
        ) : Event

        data class CreateGroup(
            val name: String,
            val createdByUserId: Long
        ) : Event

        data class UpdateGroup(
            val groupId: Long,
            val name: String
        ) : Event

        data class DeleteGroup(
            val groupId: Long
        ) : Event

        data class LoadGroupMembers(
            val groupId: Long
        ) : Event

        data class AddGroupMember(
            val groupId: Long,
            val userId: Long
        ) : Event

        data class DeleteGroupMember(
            val groupId: Long,
            val userId: Long
        ) : Event

        data object ClearError : Event
    }

    sealed interface Effect {

        data class ShowMessage(
            val message: String
        ) : Effect
    }
}