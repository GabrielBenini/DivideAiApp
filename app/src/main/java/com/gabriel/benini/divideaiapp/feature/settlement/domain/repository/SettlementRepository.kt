package com.gabriel.benini.divideaiapp.feature.settlement.domain.repository

import com.gabriel.benini.divideaiapp.feature.settlement.domain.model.GroupBalance
import com.gabriel.benini.divideaiapp.feature.settlement.domain.model.GroupSettlement

interface SettlementRepository {

    suspend fun getGroupBalance(groupId: Long): GroupBalance

    suspend fun getGroupSettlement(groupId: Long): GroupSettlement
}