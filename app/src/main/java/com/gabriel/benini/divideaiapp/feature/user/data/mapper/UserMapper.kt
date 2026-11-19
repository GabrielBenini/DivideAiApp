package com.gabriel.benini.divideaiapp.feature.user.data.mapper

import com.gabriel.benini.divideaiapp.feature.user.data.remote.dto.response.UserResponseDto
import com.gabriel.benini.divideaiapp.feature.user.domain.model.User
import java.time.LocalDateTime

fun UserResponseDto.toDomain(): User {
    return User(
        id = id,
        name = name,
        createdAt = LocalDateTime.parse(createdAt)
    )
}