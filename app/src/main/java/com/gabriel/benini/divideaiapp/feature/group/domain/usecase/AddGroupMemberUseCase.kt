package com.gabriel.benini.divideaiapp.feature.group.domain.usecase

import com.gabriel.benini.divideaiapp.feature.group.domain.model.GroupMember
import com.gabriel.benini.divideaiapp.feature.group.domain.repository.GroupRepository
import javax.inject.Inject

class AddGroupMemberUseCase @Inject constructor(
    private val groupRepository: GroupRepository
) {

    suspend operator fun invoke(groupId: Long, userId: Long): GroupMember{
        return groupRepository.addGroupMember(groupId, userId)
    }
}