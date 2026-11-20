package com.gabriel.benini.divideaiapp.feature.group.domain.usecase

import com.gabriel.benini.divideaiapp.feature.group.domain.repository.GroupRepository
import javax.inject.Inject

class DeleteGroupMemberUseCase @Inject constructor(
    private val groupRepository: GroupRepository
) {

    suspend operator fun invoke(groupId: Long, userId: Long){
        groupRepository.deleteGroupMember(groupId, userId)
    }
}