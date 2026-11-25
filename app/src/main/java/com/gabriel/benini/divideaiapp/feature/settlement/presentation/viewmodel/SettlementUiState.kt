package com.gabriel.benini.divideaiapp.feature.settlement.presentation.viewmodel

import com.gabriel.benini.divideaiapp.feature.settlement.domain.model.GroupBalance
import com.gabriel.benini.divideaiapp.feature.settlement.domain.model.GroupSettlement

sealed interface SettlementUiState {
    data object Loading: SettlementUiState
    data class GroupBalanceSuccess(val balance: GroupBalance): SettlementUiState
    data class Error(val message: String): SettlementUiState
    data class GroupSettlementSuccess(val settlement: GroupSettlement): SettlementUiState
}