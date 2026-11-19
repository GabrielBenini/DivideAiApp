package com.gabriel.beini.divideaiapp.feature.group.data.repository

import com.gabriel.beini.divideaiapp.feature.group.data.mapper.toDomain
import com.gabriel.beini.divideaiapp.feature.group.data.remote.api.GroupApi
import com.gabriel.beini.divideaiapp.feature.group.data.remote.dto.request.CreateGroupRequestDto
import com.gabriel.beini.divideaiapp.feature.group.domain.model.Group
import com.gabriel.beini.divideaiapp.feature.group.domain.repository.GroupRepository
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
}