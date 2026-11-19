package com.gabriel.benini.divideaiapp.feature.group.domain.model

import java.time.LocalDateTime

data class GroupMember(
    val id: Long,
    val userId: Long,
    val userName: String,
    val joinedAt: LocalDateTime
)
