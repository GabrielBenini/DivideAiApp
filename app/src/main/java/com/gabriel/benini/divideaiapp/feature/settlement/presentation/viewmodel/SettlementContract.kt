package com.gabriel.benini.divideaiapp.feature.settlement.presentation.viewmodel

import com.gabriel.benini.divideaiapp.feature.settlement.domain.model.GroupBalance
import com.gabriel.benini.divideaiapp.feature.settlement.domain.model.GroupSettlement

object SettlementContract {

    data class State(
        val isLoading: Boolean = false,
        val balance: GroupBalance? = null,
        val settlement: GroupSettlement? = null,
        val errorMessage: String? = null
    )

    sealed interface Event {

        data class LoadGroupBalance(
            val groupId: Long
        ) : Event

        data class LoadGroupSettlement(
            val groupId: Long
        ) : Event

        data object ClearError : Event
    }
}