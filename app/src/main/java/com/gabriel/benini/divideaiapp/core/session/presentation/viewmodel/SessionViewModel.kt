package com.gabriel.benini.divideaiapp.core.session.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.benini.divideaiapp.core.session.domain.usecase.ObserveSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val observeSessionUseCase: ObserveSessionUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow(SessionContract.State())
    val uiState = _uiState.asStateFlow()

    init {
        observeSession()
    }

    private fun observeSession(){
        viewModelScope.launch {
            try {
                observeSessionUseCase().collect { id ->
                    _uiState.update {
                        it.copy(
                            userId = id,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
            } catch (e: CancellationException){
                throw e
            } catch (e: Exception){
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Erro ao recuperar sessao"
                    )
                }
            }
        }
    }
}