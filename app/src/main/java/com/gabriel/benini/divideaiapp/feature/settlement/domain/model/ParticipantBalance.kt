package com.gabriel.benini.divideaiapp.feature.settlement.domain.model

data class ParticipantBalance(
    val userId: Long,
    val userName: String,
    val totalPaidInCents: Long,
    val shareAmountInCents: Long,
    val balanceInCents: Long
)
