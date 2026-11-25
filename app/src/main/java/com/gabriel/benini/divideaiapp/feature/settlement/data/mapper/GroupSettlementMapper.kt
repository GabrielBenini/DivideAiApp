package com.gabriel.benini.divideaiapp.feature.settlement.data.mapper

import com.gabriel.benini.divideaiapp.feature.settlement.data.remote.dto.response.GroupSettlementResponseDto
import com.gabriel.benini.divideaiapp.feature.settlement.domain.model.GroupSettlement

fun GroupSettlementResponseDto.toDomain(): GroupSettlement{
    return GroupSettlement(
        groupId = groupId,
        groupName = groupName,
        totalAmountInCents = totalAmountInCents,
        participantCount = participantCount,
        participants = participants.map { participant ->
            participant.toDomain()
        },
        transfers = transfers.map { transfer ->
            transfer.toDomain()
        }
    )
}