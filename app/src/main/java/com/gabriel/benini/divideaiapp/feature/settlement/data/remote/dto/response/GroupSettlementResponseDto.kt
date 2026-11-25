package com.gabriel.benini.divideaiapp.feature.settlement.data.remote.dto.response

data class GroupSettlementResponseDto(
    val groupId: Long,
    val groupName: String,
    val totalAmountInCents: Long,
    val participantCount: Int,
    val participants: List<ParticipantBalanceResponseDto>,
    val transfers: List<TransferResponseDto>
)
