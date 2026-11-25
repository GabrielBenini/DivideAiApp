package com.gabriel.benini.divideaiapp.feature.settlement.domain.model

data class GroupBalance(
    val groupId: Long,
    val groupName: String,
    val totalAmountInCents: Long,
    val participantCount: Int,
    val participants: List<ParticipantBalance>
)
