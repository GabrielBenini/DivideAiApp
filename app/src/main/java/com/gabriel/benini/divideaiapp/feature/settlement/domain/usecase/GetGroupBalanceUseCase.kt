package com.gabriel.benini.divideaiapp.feature.settlement.domain.usecase

import com.gabriel.benini.divideaiapp.feature.settlement.domain.model.GroupBalance
import com.gabriel.benini.divideaiapp.feature.settlement.domain.repository.SettlementRepository
import javax.inject.Inject

class GetGroupBalanceUseCase @Inject constructor(
    private val settlementRepository: SettlementRepository
) {

    suspend operator fun invoke(groupId: Long): GroupBalance {
        return settlementRepository.getGroupBalance(groupId)
    }
}