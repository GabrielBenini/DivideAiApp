package com.gabriel.benini.divideaiapp.feature.group.domain.repository

import com.gabriel.benini.divideaiapp.feature.group.domain.model.Group

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
}