package com.gabriel.benini.divideaiapp.feature.group.domain.usecase

import com.gabriel.benini.divideaiapp.feature.group.domain.model.Group
import com.gabriel.benini.divideaiapp.feature.group.domain.repository.GroupRepository
import javax.inject.Inject

class GetGroupByIdUseCase @Inject constructor(
    private val groupRepository: GroupRepository
) {

    suspend operator fun invoke(id: Long): Group{
        return groupRepository.getGroupById(id)
    }
}