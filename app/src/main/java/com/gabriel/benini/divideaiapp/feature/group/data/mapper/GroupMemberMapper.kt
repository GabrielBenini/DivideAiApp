package com.gabriel.benini.divideaiapp.feature.group.data.mapper

import com.gabriel.benini.divideaiapp.feature.group.data.remote.dto.response.GroupMemberResponseDto
import com.gabriel.benini.divideaiapp.feature.group.domain.model.GroupMember
import java.time.LocalDateTime

fun GroupMemberResponseDto.toDomain(): GroupMember{
    return GroupMember(
        id = id,
        userId = userId,
        userName = userName,
        joinedAt = LocalDateTime.parse(joinedAt)
    )
}