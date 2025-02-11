package bts.sio.azurimmo.views.Batiment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewsmodel.batiment.BatimentViewModel

// Fonction Composable pour afficher la liste des bâtiments
@Composable
fun BatimentList(
    viewModel: BatimentViewModel = viewModel(),
    onBatimentClick: (Int) -> Unit,
    onAddBatimentClick: () -> Unit // Callback pour ajouter un bâtiment
) {

    val batiments = viewModel.batiments.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    // Observer les données des bâtiments via le ViewModel
    Box(modifier = Modifier.fillMaxSize()) {
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

            else -> {
                Column {
                    Button(
                        onClick = onAddBatimentClick,
                        modifier = Modifier
                            .widthIn(min = 150.dp, max = 300.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(16.dp)
                    ) {
                        Text("Ajouter un bâtiment")
                    }

                    LazyColumn {

                        // Ajout du titre avant la liste des bâtiments
                        item {
                            Text(
                                text = "Liste des bâtiments",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp, horizontal = 16.dp),
                                textAlign = TextAlign.Center, // Centre le texte
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
            }
        }
    }
}

