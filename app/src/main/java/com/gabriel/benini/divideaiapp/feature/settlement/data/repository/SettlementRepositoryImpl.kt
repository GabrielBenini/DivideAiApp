package com.gabriel.benini.divideaiapp.feature.settlement.data.repository

import com.gabriel.benini.divideaiapp.feature.settlement.data.mapper.toDomain
import com.gabriel.benini.divideaiapp.feature.settlement.data.remote.api.SettlementApi
import com.gabriel.benini.divideaiapp.feature.settlement.domain.model.GroupBalance
import com.gabriel.benini.divideaiapp.feature.settlement.domain.model.GroupSettlement
import com.gabriel.benini.divideaiapp.feature.settlement.domain.repository.SettlementRepository
import javax.inject.Inject

class SettlementRepositoryImpl @Inject constructor(
    private val settlementApi: SettlementApi
): SettlementRepository{

    override suspend fun getGroupBalance(groupId: Long): GroupBalance {
        return settlementApi.getGroupBalance(groupId).toDomain()
    }

    override suspend fun getGroupSettlement(groupId: Long): GroupSettlement {
        return settlementApi.getGroupSettlement(groupId).toDomain()
    }
}