package com.gabriel.benini.divideaiapp.feature.settlement.data.remote.dto.response

data class TransferResponseDto(
    val fromUserId: Long,
    val fromUserName: String,
    val toUserId: Long,
    val toUserName: String,
    val amountInCents: Long
)
