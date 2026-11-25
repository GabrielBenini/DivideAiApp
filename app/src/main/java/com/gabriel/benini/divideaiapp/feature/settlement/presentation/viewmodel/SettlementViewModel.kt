package com.gabriel.benini.divideaiapp.feature.settlement.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.benini.divideaiapp.feature.settlement.domain.usecase.GetGroupBalanceUseCase
import com.gabriel.benini.divideaiapp.feature.settlement.domain.usecase.GetGroupSettlementUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettlementViewModel @Inject constructor(
    private val getGroupBalanceUseCase: GetGroupBalanceUseCase,
    private val getGroupSettlementUseCase: GetGroupSettlementUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettlementContract.State())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: SettlementContract.Event) {
        when (event) {

            is SettlementContract.Event.LoadGroupBalance ->
                loadGroupBalance(event.groupId)

            is SettlementContract.Event.LoadGroupSettlement ->
                loadGroupSettlement(event.groupId)

            SettlementContract.Event.ClearError ->
                clearError()
        }
    }

    private fun loadGroupBalance(groupId: Long) {
        viewModelScope.launch {
            startLoading()

            try {
                val balance = getGroupBalanceUseCase(groupId)

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        balance = balance,

                        settlement = state.settlement
                            ?.takeIf { it.groupId == groupId }
                    )
                }

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(
                    e,
                    "Erro ao consultar saldo do grupo"
                )
            }
        }
    }

    private fun loadGroupSettlement(groupId: Long) {
        viewModelScope.launch {
            startLoading()

            try {
                val settlement = getGroupSettlementUseCase(groupId)

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        settlement = settlement,

                        balance = state.balance
                            ?.takeIf { it.groupId == groupId }
                    )
                }

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                handleError(
                    e,
                    "Erro ao consultar fechamento do grupo"
                )
            }
        }
    }

    private fun startLoading() {
        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }
    }

    private fun handleError(
        exception: Exception,
        defaultMessage: String
    ) {
        _uiState.update {
            it.copy(
                isLoading = false,
                errorMessage = exception.message ?: defaultMessage
            )
        }
    }

    private fun clearError() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }
}