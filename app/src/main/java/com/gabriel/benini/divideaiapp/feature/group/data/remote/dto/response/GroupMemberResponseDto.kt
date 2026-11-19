package com.gabriel.benini.divideaiapp.feature.group.data.remote.dto.response

data class GroupMemberResponseDto(
    val id: Long,
    val userId: Long,
    val userName: String,
    val joinedAt: String,
)
