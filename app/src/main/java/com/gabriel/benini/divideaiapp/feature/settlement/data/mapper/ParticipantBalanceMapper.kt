package com.gabriel.benini.divideaiapp.feature.settlement.data.mapper

import com.gabriel.benini.divideaiapp.feature.settlement.data.remote.dto.response.ParticipantBalanceResponseDto
import com.gabriel.benini.divideaiapp.feature.settlement.domain.model.ParticipantBalance

fun ParticipantBalanceResponseDto.toDomain(): ParticipantBalance{
    return ParticipantBalance(
        userId = userId,
        userName = userName,
        totalPaidInCents = totalPaidInCents,
        shareAmountInCents = shareAmountInCents,
        balanceInCents = balanceInCents
    )
}