package com.gabriel.benini.divideaiapp.feature.settlement.data.remote.dto.response

data class GroupBalanceResponseDto(
    val groupId: Long,
    val groupName: String,
    val totalAmountInCents: Long,
    val participantCount: Int,
    val participants: List<ParticipantBalanceResponseDto>
)
