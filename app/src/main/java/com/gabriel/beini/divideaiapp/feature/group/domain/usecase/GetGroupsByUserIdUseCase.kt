package com.gabriel.beini.divideaiapp.feature.group.domain.usecase

import com.gabriel.beini.divideaiapp.feature.group.domain.model.Group
import com.gabriel.beini.divideaiapp.feature.group.domain.repository.GroupRepository
import javax.inject.Inject


class GetGroupsByUserIdUseCase @Inject constructor(
    private val groupRepository: GroupRepository
) {

    suspend operator fun invoke(userId: Long): List<Group>{
        return groupRepository.getGroupsByUserId(userId)
    }
}