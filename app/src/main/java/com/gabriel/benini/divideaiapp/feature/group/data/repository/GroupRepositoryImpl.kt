package com.gabriel.benini.divideaiapp.feature.group.data.repository

import com.gabriel.benini.divideaiapp.feature.group.data.mapper.toDomain
import com.gabriel.benini.divideaiapp.feature.group.data.remote.api.GroupApi
import com.gabriel.benini.divideaiapp.feature.group.data.remote.dto.request.AddGroupMemberRequestDto
import com.gabriel.benini.divideaiapp.feature.group.data.remote.dto.request.CreateGroupRequestDto
import com.gabriel.benini.divideaiapp.feature.group.data.remote.dto.request.UpdateGroupRequestDto
import com.gabriel.benini.divideaiapp.feature.group.domain.model.Group
import com.gabriel.benini.divideaiapp.feature.group.domain.model.GroupMember
import com.gabriel.benini.divideaiapp.feature.group.domain.repository.GroupRepository
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val groupApi: GroupApi
): GroupRepository {

    override suspend fun getGroupById(id: Long): Group{
        return groupApi.getGroupById(id).toDomain()
    }

    override suspend fun getGroupsByUserId(userId: Long): List<Group> {
        return groupApi.getGroupsByUserId(userId).map { groupResponseDto ->
            groupResponseDto.toDomain()
        }
    }

    override suspend fun createGroup(
        name: String,
        createdByUserId: Long
    ): Group {
        val request = CreateGroupRequestDto(
            name = name,
            createdByUserId = createdByUserId
        )

        return groupApi.createGroup(request).toDomain()
    }

    override suspend fun updateGroup(groupId: Long, name: String): Group {
        return groupApi.updateGroup(groupId, UpdateGroupRequestDto(name)).toDomain()
    }

    override suspend fun deleteGroup(groupId: Long) {
        return groupApi.deleteGroup(groupId)
    }

    override suspend fun getGroupMembers(groupId: Long): List<GroupMember> {
        return groupApi.getGroupMembers(groupId).map { groupMemberResponseDto ->
            groupMemberResponseDto.toDomain()
        }
    }

    override suspend fun addGroupMember(groupId: Long, userId: Long): GroupMember {
        return groupApi.addGroupMember(groupId, AddGroupMemberRequestDto(userId)).toDomain()
    }
}