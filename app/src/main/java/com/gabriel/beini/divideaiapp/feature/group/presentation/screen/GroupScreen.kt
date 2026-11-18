package com.gabriel.beini.divideaiapp.feature.group.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gabriel.beini.divideaiapp.feature.group.presentation.viewmodel.GroupUiState
import com.gabriel.beini.divideaiapp.feature.group.presentation.viewmodel.GroupViewModel
import com.gabriel.beini.divideaiapp.feature.user.presentation.viewmodel.UserUiState

@Composable
fun GroupScreen(
    viewModel: GroupViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadGroupById(1)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {

        when (val state = uiState) {

            is GroupUiState.Loading -> {
                CircularProgressIndicator()
            }

            is GroupUiState.Success -> {

                Column() {
                    Text(text = "ID: ${state.group.id}")
                    Text(text = state.group.name)
                }
            }

            is GroupUiState.Error -> {
                Text(text = state.message)
            }
        }
    }
}