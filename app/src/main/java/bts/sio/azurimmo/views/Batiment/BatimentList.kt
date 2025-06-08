package bts.sio.azurimmo.views.Batiment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import bts.sio.azurimmo.viewsmodel.batiment.BatimentViewModel

// Fonction Composable pour afficher la liste des bâtiments
@Composable
fun BatimentList(
    viewModel: BatimentViewModel = viewModel(),
    onBatimentClick: (Int) -> Unit,
    onAddBatimentClick: () -> Unit,
    navController: NavController

    ) {

    val batiments = viewModel.batiments.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    LaunchedEffect(Unit) {
        viewModel.getBatiments()
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Navigation vers l'écran d'ajout
                navController.navigate("add_batiment")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Ajouter un batiment")
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
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }


                    errorMessage != null -> {
                        Text(
                            text = errorMessage ?: "Erreur inconnue",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(16.dp),
                            color = MaterialTheme.colorScheme.error
                        )
                    }


//    // Observer les données des bâtiments via le ViewModel
//    Box(modifier = Modifier.fillMaxSize()) {
//        when {
//            isLoading -> {
//                CircularProgressIndicator(
//                    modifier = Modifier.align(Alignment.Center)
//                )
//            }
//
//            errorMessage != null -> {
//                Text(
//                    text = errorMessage ?: "Erreur inconnue",
//                    modifier = Modifier
//                        .align(Alignment.Center)
//                        .padding(16.dp),
//                    color = MaterialTheme.colorScheme.error
//                )
//            }

                    else -> {
                        Column(modifier = Modifier.fillMaxSize()) {
                            LazyColumn(modifier = Modifier.weight(1f)) {
                                item {
                                    Text(
                                        text = "Liste des bâtiments",
                                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 8.dp, horizontal = 16.dp),
                                        textAlign = TextAlign.Center,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                                items(batiments) { batiment ->
                                    BatimentCard(
                                        batiment = batiment,
                                        onClick = { onBatimentClick(batiment.id) }
                                    )
                                }
                            }
                        }

//                FloatingActionButton(
//                    onClick = onAddBatimentClick,
//                    modifier = Modifier
//                        .align(Alignment.BottomEnd)
//                        .padding(16.dp)
//                ) {
//                    Icon(Icons.Default.Add, contentDescription = "Ajouter un bâtiment")
//                }
                    }
                }
            }
        }
    )
}


