package com.gabriel.beini.divideaiapp.feature.group.presentation.viewmodel

import com.gabriel.beini.divideaiapp.feature.group.domain.model.Group

sealed interface GroupUiState{

    data object Loading: GroupUiState
    data class Success(val group: Group): GroupUiState
    data class Error(val message: String): GroupUiState
}