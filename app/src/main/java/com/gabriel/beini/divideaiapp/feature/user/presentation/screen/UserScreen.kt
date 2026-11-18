
package com.gabriel.beini.divideaiapp.feature.user.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gabriel.beini.divideaiapp.feature.user.presentation.viewmodel.UserUiState
import com.gabriel.beini.divideaiapp.feature.user.presentation.viewmodel.UserViewModel

@Composable
fun UserScreen(
    viewModel: UserViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
//        viewModel.deleteUser(2)
        viewModel.loadUsers()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        when (val state = uiState) {

            is UserUiState.Loading -> {
                CircularProgressIndicator()
            }

            is UserUiState.Success -> {
                if (state.users.isEmpty()) {
                    Text(text = "Nenhum usuário cadastrado")
                }
                else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = state.users,
                            key = { user -> user.id }
                        ) { user ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(text = user.name)
                                Text(text = "ID: ${user.id}")
                            }
                        }
                    }
                }
            }

            is UserUiState.UserDetailSuccess -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(text = "ID: ${state.user.id}")
                    Text(text = state.user.name)
                }
            }

            is UserUiState.DeletedUserSuccess -> {
                Text(text = "Usuário excluído com sucesso!")
            }

            is UserUiState.Error -> {
                Text(text = state.message)
            }
        }
    }
}
