package bts.sio.azurimmo.views.Contrat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewsmodel.contrat.ContratViewModel
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

// Fonction Composable pour afficher la liste des contrats
@Composable
fun ContratList(navController: NavController, viewModel: ContratViewModel = viewModel()) {

    val contrats by viewModel.contrats
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    // Recharge automatique quand l'écran devient actif
    LaunchedEffect(Unit) {
        viewModel.getContrats()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Navigation vers l'écran d'ajout
                navController.navigate("contratAdd")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Ajouter un contrat")
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when {
                    isLoading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    errorMessage != null -> {
                        Text(
                            text = errorMessage,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(16.dp),
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    else -> {

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp) // ✅ padding général pour la liste
                        ) {
                            if (contrats.isNotEmpty()) {
                                item {
                                    Text(
                                        text = "Liste des contrats",
                                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 16.dp),
                                        textAlign = TextAlign.Center,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                            items(contrats) { contrat ->
                                ContratCard(contrat = contrat)
                            }
                        }
                    }
                }
            }
        }
    )
}
