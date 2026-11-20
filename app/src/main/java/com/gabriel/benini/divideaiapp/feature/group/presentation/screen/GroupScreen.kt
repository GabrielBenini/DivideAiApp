package com.gabriel.benini.divideaiapp.feature.group.presentation.screen

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gabriel.benini.divideaiapp.feature.group.presentation.viewmodel.GroupUiState
import com.gabriel.benini.divideaiapp.feature.group.presentation.viewmodel.GroupViewModel

@Composable
fun GroupScreen(
    viewModel: GroupViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getGroupMembers(2)
//        viewModel.deleteGroupMember(2,3)
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

            is GroupUiState.GroupMemberSuccess -> {
                Column() {
                    Text(text = "${state.member.userName} adicionado com sucesso ao grupo.")
                }
            }

            is GroupUiState.DeletedGroupMemberSuccess -> {
                Column() {
                    Text(text = "Usuario excluido com sucesso do grupo.")
                }
            }

            is GroupUiState.GroupMembersSuccess -> {

                if (state.members.isEmpty()){
                    Text("Nenhum membro no grupo")
                } else{

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = state.members,
                            key = { member -> member.id }
                        ) { member ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(text = "User id: ${member.userId}")
                                Text(text = member.userName)
                                Text(text = "Joined at: ${member.joinedAt}")
                            }
                        }
                    }
                }
            }

            is GroupUiState.GroupsListSuccess -> {

                if (state.groups.isEmpty()) {
                    Text("Nenhum Grupo Encontrado")
                } else {

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = state.groups,
                            key = { group -> group.id }
                        ) { group ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                            ) {
                                Text(text = group.name)
                                Text(text = "ID: ${group.id}")
                            }
                        }

                    }
                }
            }

            is GroupUiState.Success -> {

                Column() {
                    Text(text = "ID: ${state.group.id}")
                    Text(text = state.group.name)
                }
            }

            is GroupUiState.DeletedGroupSuccess -> {
                Text(text = "Grupo excluido com sucesso")
            }

            is GroupUiState.Error -> {
                Text(text = state.message)
            }
        }
    }
}