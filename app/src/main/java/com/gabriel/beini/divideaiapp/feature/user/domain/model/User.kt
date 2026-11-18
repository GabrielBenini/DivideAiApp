package com.gabriel.beini.divideaiapp.feature.user.domain.model

import java.time.LocalDateTime

data class User(
    val id: Long,
    val name: String,
    val createdAt: LocalDateTime,
)
