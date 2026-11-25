package com.gabriel.benini.divideaiapp.feature.settlement.data.mapper

import com.gabriel.benini.divideaiapp.feature.settlement.data.remote.dto.response.TransferResponseDto
import com.gabriel.benini.divideaiapp.feature.settlement.domain.model.Transfer

fun TransferResponseDto.toDomain(): Transfer{
    return Transfer(
        fromUserId = fromUserId,
        fromUserName = fromUserName,
        toUserId = toUserId,
        toUserName = toUserName,
        amountInCents = amountInCents
    )
}