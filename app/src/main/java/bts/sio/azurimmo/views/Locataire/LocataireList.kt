package bts.sio.azurimmo.views.Locataire

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
import bts.sio.azurimmo.views.Contrat.ContratCard
import bts.sio.azurimmo.viewsmodel.Locataire.LocataireViewModel
import bts.sio.azurimmo.viewsmodel.contrat.ContratViewModel


// Fonction Composable pour afficher la liste des locataires
@Composable
fun LocataireList(viewModel: LocataireViewModel = viewModel()) {
// Observer les données des locataires via le ViewModel
    val locataires = viewModel.locataires.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

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
                LazyColumn {
                    // Bloc liste des contrats
                    if (locataires.isNotEmpty()) {
                        /* BLOC AVEC LISTE DES LOCATAIRES *********************/
                        // Ajouter un titre pour la liste des locataires
                        item {
                            Text(
                                text = "Liste des locataires",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    items(locataires) { locataire ->
                        LocataireCard(locataire = locataire) // Appel de la fonction LocataireCard
                    }
                }
            }
        }
    }
    }
}

