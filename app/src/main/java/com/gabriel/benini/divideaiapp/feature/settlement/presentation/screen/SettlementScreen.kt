package com.gabriel.benini.divideaiapp.feature.settlement.presentation.screen

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gabriel.benini.divideaiapp.feature.settlement.presentation.viewmodel.SettlementContract
import com.gabriel.benini.divideaiapp.feature.settlement.presentation.viewmodel.SettlementViewModel

@Composable
fun SettlementScreen(
    viewModel: SettlementViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onEvent(
            SettlementContract.Event.LoadGroupSettlement(3L)
        )
    }

    Scaffold { innerPadding ->

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

                uiState.balance?.let { balance ->

                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text("Saldo do grupo")
                            Text("Group ID: ${balance.groupId}")
                            Text("Group Name: ${balance.groupName}")
                            Text("Total: ${balance.totalAmountInCents}")
                            Text("Participants: ${balance.participantCount}")

                            HorizontalDivider()
                        }
                    }

                    if (balance.participants.isEmpty()) {
                        item {
                            Text("Nenhum participante encontrado")
                        }
                    }

                    items(
                        items = balance.participants,
                        key = { participant ->
                            "participant-${participant.userId}"
                        }
                    ) { participant ->

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text("User ID: ${participant.userId}")
                            Text("Nome: ${participant.userName}")
                            Text("Total pago: ${participant.totalPaidInCents}")
                            Text("Parte individual: ${participant.shareAmountInCents}")
                            Text("Saldo: ${participant.balanceInCents}")

                            HorizontalDivider()
                        }
                    }
                }

                uiState.settlement?.let { settlement ->

                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text("Fechamento do grupo")
                            Text("Group ID: ${settlement.groupId}")
                            Text("Group Name: ${settlement.groupName}")
                            Text("Total: ${settlement.totalAmountInCents}")
                            Text("Participants: ${settlement.participantCount}")

                            HorizontalDivider()

                            Text("Transferências necessárias")
                        }
                    }

                    if (settlement.transfers.isEmpty()) {
                        item {
                            Text("Nenhuma transferência necessária")
                        }
                    }

                    items(
                        items = settlement.transfers,
                        key = { transfer ->
                            "transfer-${transfer.fromUserId}-${transfer.toUserId}"
                        }
                    ) { transfer ->

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text("From User ID: ${transfer.fromUserId}")
                            Text("De: ${transfer.fromUserName}")
                            Text("To User ID: ${transfer.toUserId}")
                            Text("Para: ${transfer.toUserName}")
                            Text("Valor: ${transfer.amountInCents}")

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