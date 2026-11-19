package com.gabriel.beini.divideaiapp.feature.group.domain.usecase

import com.gabriel.beini.divideaiapp.feature.group.domain.model.Group
import com.gabriel.beini.divideaiapp.feature.group.domain.repository.GroupRepository
import javax.inject.Inject

class UpdateGroupUseCase @Inject constructor(
    private val groupRepository: GroupRepository
) {

    suspend operator fun invoke(groupId: Long, name: String): Group{
        return groupRepository.updateGroup(groupId, name)
    }
}