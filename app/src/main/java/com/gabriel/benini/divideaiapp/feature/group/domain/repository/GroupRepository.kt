package com.gabriel.benini.divideaiapp.feature.group.domain.repository

import com.gabriel.benini.divideaiapp.feature.group.domain.model.Group
import com.gabriel.benini.divideaiapp.feature.group.domain.model.GroupMember

interface GroupRepository {

    suspend fun getGroupById(id: Long): Group

    suspend fun getGroupsByUserId(userId: Long): List<Group>

    suspend fun createGroup(
        name: String,
        createdByUserId: Long
    ): Group

    suspend fun updateGroup(
        groupId: Long,
        name: String
    ): Group

    suspend fun deleteGroup(
        groupId: Long
    )

    suspend fun getGroupMembers(
        groupId: Long
    ): List<GroupMember>

    suspend fun addGroupMember(
        groupId: Long,
        userId: Long
    ): GroupMember

    suspend fun deleteGroupMember(
        groupId: Long,
        userId: Long
    )
}