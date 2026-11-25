package com.gabriel.benini.divideaiapp.feature.group.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gabriel.benini.divideaiapp.feature.group.presentation.viewmodel.GroupContract
import com.gabriel.benini.divideaiapp.feature.group.presentation.viewmodel.GroupViewModel

@Composable
fun GroupScreen(
    viewModel: GroupViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is GroupContract.Effect.ShowMessage -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(
            GroupContract.Event.LoadGroupsByUserId(1L)
        )
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                uiState.errorMessage?.let { message ->
                    item {
                        Text(text = message)
                    }
                }

                uiState.selectedGroup?.let { group ->
                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text("Grupo selecionado")
                            Text("ID: ${group.id}")
                            Text("Nome: ${group.name}")
                            Text("Created at: ${group.createdAt}")

                            HorizontalDivider()
                        }
                    }
                }

                item {
                    Text("Lista de grupos")
                }

                if (uiState.groups.isEmpty() && !uiState.isLoading) {
                    item {
                        Text("Nenhum grupo na lista")
                    }
                }

                items(
                    items = uiState.groups,
                    key = { group -> "group-${group.id}" }
                ) { group ->

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text("ID: ${group.id}")
                        Text("Nome: ${group.name}")

                        HorizontalDivider()
                    }
                }

                uiState.membersGroupId?.let { groupId ->

                    item {
                        Text("Membros do grupo $groupId")
                    }

                    if (uiState.members.isEmpty() && !uiState.isLoading) {
                        item {
                            Text("Nenhum membro encontrado")
                        }
                    }

                    items(
                        items = uiState.members,
                        key = { member -> "member-${member.id}" }
                    ) { member ->

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text("User ID: ${member.userId}")
                            Text("Nome: ${member.userName}")
                            Text("Joined at: ${member.joinedAt}")

                            HorizontalDivider()
                        }
                    }
                }
            }

            if (uiState.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}