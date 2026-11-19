package com.gabriel.benini.divideaiapp.feature.group.domain.usecase

import com.gabriel.benini.divideaiapp.feature.group.domain.model.Group
import com.gabriel.benini.divideaiapp.feature.group.domain.repository.GroupRepository
import javax.inject.Inject

class CreateGroupUseCase @Inject constructor(
    private val groupRepository: GroupRepository
) {

    suspend operator fun invoke(
        name: String,
        createdByUserId: Long
    ): Group {
        return groupRepository.createGroup(name, createdByUserId)
    }
}