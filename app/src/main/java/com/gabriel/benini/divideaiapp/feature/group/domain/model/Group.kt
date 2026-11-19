package com.gabriel.benini.divideaiapp.feature.group.domain.model

import java.time.LocalDateTime

data class Group(
    val id: Long,
    val name: String,
    val createdAt: LocalDateTime
)
