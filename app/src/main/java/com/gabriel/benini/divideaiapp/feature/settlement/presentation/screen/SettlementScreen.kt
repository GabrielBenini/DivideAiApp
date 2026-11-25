package com.gabriel.benini.divideaiapp.feature.settlement.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gabriel.benini.divideaiapp.feature.settlement.presentation.viewmodel.SettlementUiState
import com.gabriel.benini.divideaiapp.feature.settlement.presentation.viewmodel.SettlementViewModel

@Composable
fun SettlementScreen(
    viewModel: SettlementViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getGroupBalance(groupId = 3L)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        when (val state = uiState) {
            SettlementUiState.Loading -> {
                CircularProgressIndicator()
            }

            is SettlementUiState.GroupBalanceSuccess -> {
                val balance = state.balance

                Text("Group ID: ${balance.groupId}")
                Text("Group Name: ${balance.groupName}")
                Text("Total Amount: ${balance.totalAmountInCents}")
                Text("Participants: ${balance.participantCount}")

                HorizontalDivider()

                if (balance.participants.isEmpty()) {
                    Text("Nenhum participante encontrado")
                } else {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(
                            items = balance.participants,
                            key = { it.userId }
                        ) { participant ->
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text("User ID: ${participant.userId}")
                                Text("Name: ${participant.userName}")
                                Text("Total Paid: ${participant.totalPaidInCents}")
                                Text("Share Amount: ${participant.shareAmountInCents}")
                                Text("Balance: ${participant.balanceInCents}")

                                HorizontalDivider()
                            }
                        }
                    }
                }
            }

            is SettlementUiState.Error -> {
                Text(state.message)
            }
        }
    }
}