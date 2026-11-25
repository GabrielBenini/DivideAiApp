package com.gabriel.benini.divideaiapp.feature.settlement.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.benini.divideaiapp.feature.settlement.domain.usecase.GetGroupBalanceUseCase
import com.gabriel.benini.divideaiapp.feature.settlement.domain.usecase.GetGroupSettlementUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettlementViewModel @Inject constructor(
    private val getGroupBalanceUseCase: GetGroupBalanceUseCase,
    private val getGroupSettlementUseCase: GetGroupSettlementUseCase,
): ViewModel(){
    private val _uiState = MutableStateFlow<SettlementUiState>(
        value = SettlementUiState.Loading
    )

    val uiState = _uiState.asStateFlow()

    fun getGroupBalance(groupId: Long){
        viewModelScope.launch {
            _uiState.value = SettlementUiState.Loading

            try {
                val groupBalance = getGroupBalanceUseCase(groupId)
                _uiState.value = SettlementUiState.GroupBalanceSuccess(groupBalance)
            } catch (e: CancellationException){
                throw e
            } catch (e: Exception){
                _uiState.value = SettlementUiState.Error(
                    message = e.message ?: "Erro ao consultar saldo do grupo"
                )
            }
        }
    }

    fun getGroupSettlement(groupId: Long){
        viewModelScope.launch {
            _uiState.value = SettlementUiState.Loading

            try {
                val groupSettlement = getGroupSettlementUseCase(groupId)
                _uiState.value = SettlementUiState.GroupSettlementSuccess(groupSettlement)
            } catch (e: CancellationException){
                throw e
            } catch (e: Exception){
                _uiState.value = SettlementUiState.Error(
                    message = e.message ?: "Erro ao consultar settlement do grupo"
                )
            }
        }
    }
}