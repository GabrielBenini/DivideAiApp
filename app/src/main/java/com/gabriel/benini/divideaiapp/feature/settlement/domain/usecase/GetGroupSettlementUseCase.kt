package com.gabriel.benini.divideaiapp.feature.settlement.domain.usecase

import com.gabriel.benini.divideaiapp.feature.settlement.domain.model.GroupSettlement
import com.gabriel.benini.divideaiapp.feature.settlement.domain.repository.SettlementRepository
import javax.inject.Inject

class GetGroupSettlementUseCase @Inject constructor(
    private val settlementRepository: SettlementRepository
) {

    suspend operator fun invoke(groupId: Long): GroupSettlement {
        return settlementRepository.getGroupSettlement(groupId)
    }
}