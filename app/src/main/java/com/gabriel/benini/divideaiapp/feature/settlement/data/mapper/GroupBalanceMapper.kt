package com.gabriel.benini.divideaiapp.feature.settlement.data.mapper

import com.gabriel.benini.divideaiapp.feature.settlement.data.remote.dto.response.GroupBalanceResponseDto
import com.gabriel.benini.divideaiapp.feature.settlement.domain.model.GroupBalance

fun GroupBalanceResponseDto.toDomain(): GroupBalance{
    return GroupBalance(
        groupId = groupId,
        groupName = groupName,
        totalAmountInCents = totalAmountInCents,
        participantCount = participantCount,
        participants = participants.map { participant ->
            participant.toDomain()
        }
    )
}