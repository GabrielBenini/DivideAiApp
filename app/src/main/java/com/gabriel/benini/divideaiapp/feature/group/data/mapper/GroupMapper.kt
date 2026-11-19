package com.gabriel.benini.divideaiapp.feature.group.data.mapper

import com.gabriel.benini.divideaiapp.feature.group.data.remote.dto.response.GroupResponseDto
import com.gabriel.benini.divideaiapp.feature.group.domain.model.Group
import java.time.LocalDateTime

fun GroupResponseDto.toDomain(): Group{
    return Group(
        id = id,
        name = name,
        createdAt = LocalDateTime.parse(createdAt)
    )
}