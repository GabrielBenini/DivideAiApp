package com.gabriel.benini.divideaiapp.feature.settlement.domain.model

data class Transfer(
    val fromUserId: Long,
    val fromUserName: String,
    val toUserId: Long,
    val toUserName: String,
    val amountInCents: Long
)
