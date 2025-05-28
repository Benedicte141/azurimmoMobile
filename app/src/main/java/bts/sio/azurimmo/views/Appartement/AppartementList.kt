package bts.sio.azurimmo.views.Appartement

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewsmodel.appartement.AppartementViewModel
import bts.sio.azurimmo.viewsmodel.batiment.BatimentViewModel


// Fonction Composable pour afficher la liste des appartements
@Composable
fun AppartementList(
    viewModel: AppartementViewModel = viewModel(),
    viewModelBat: BatimentViewModel = viewModel(),
    batimentId: Int? = null,
    onAddAppartementClick: (() -> Unit)? = null
) {

    val appartements = viewModel.appartements.value
    val batiment = viewModelBat.batiment.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    LaunchedEffect(batimentId) {
        Log.d("APPARTEMENT_LIST", "Chargement avec batimentId = $batimentId")
        if (batimentId == null) {
            viewModel.loadAllAppartements() // méthode globale
        } else {
            viewModel.getAppartementsByBatimentId(batimentId)
            viewModelBat.getBatiment(batimentId)
        }
    }

    // Observer les données des appartements via le ViewModel
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

                onAddAppartementClick?.let { callback ->

                    FloatingActionButton(
                        onClick = {
                            println("Navigation + Ajout appartement pour bâtiment ID: $batimentId")
                            callback()
                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)

                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Ajouter un appartement")
                    }
                }

                LazyColumn {
                    if (batimentId != null && batiment != null) {
                        // Bloc infos bâtiment
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Informations sur le bâtiment",
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Adresse : ${batiment.adresse}")
                                Text("Ville : ${batiment.ville}")
                            }
                        }
                    }

                            // Bloc liste des appartements
                            if (appartements.isNotEmpty()) {
                                /* BLOC AVEC LISTE DES APPARTEMENTS *********************/
                                // Ajouter un titre pour la liste des appartements
                                item {
                                    Text(
                                        text = "Liste des appartements",
                                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        textAlign = TextAlign.Center,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }

                                // Liste des appartements
                                items(appartements) { appartement ->
                                    AppartementCard(appartement = appartement)
                                }
                            } else {
                                // Il n'y a pas d'appartement pour ce batiment
                                item {
                                    Text(
                                        text = if (batimentId != null) "Pas d'appartement pour ce bâtiment" else "Aucun appartement trouvé",
                                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 1.dp)
                                            .padding(16.dp),
                                        textAlign = TextAlign.Center, // Alignement à gauche
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }
                    }
                }
            }


        }