package bts.sio.azurimmo.views.Contrat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewsmodel.contrat.ContratViewModel

@Composable
fun ContratListGlobal(viewModel: ContratViewModel = viewModel()) {
    val contrats = viewModel.contrats.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    LaunchedEffect(true) {
        viewModel.loadAllContrats() // nouvelle méthode dédiée
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            errorMessage != null -> Text(
                text = errorMessage,
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.error
            )
            else -> LazyColumn {
                items(contrats) { contrat ->
                    ContratCard(contrat = contrat)
                }
            }
        }
    }
}