package com.gabriel.benini.divideaiapp.feature.settlement.data.remote.dto.response

data class ParticipantBalanceResponseDto(
    val userId: Long,
    val userName: String,
    val totalPaidInCents: Long,
    val shareAmountInCents: Long,
    val balanceInCents: Long
)
