package com.gabriel.benini.divideaiapp.feature.settlement.domain.repository

import com.gabriel.benini.divideaiapp.feature.settlement.domain.model.GroupBalance

interface SettlementRepository {

    suspend fun getGroupBalance(groupId: Long): GroupBalance
}