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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.model.Appartement
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

    var selectedAppartementForDelete by remember { mutableStateOf<Appartement?>(null) }
    var modeSuppressionActive by remember { mutableStateOf(false) }

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
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (errorMessage != null) {
            Text(
                text = errorMessage,
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.error
            )
        } else {
            LazyColumn {
                if (batimentId != null && batiment != null) {
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
                if (appartements.isNotEmpty()) {
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
                    items(appartements) { appartement ->
                        AppartementCard(
                            appartement = appartement,
                            modeSuppressionActive = modeSuppressionActive,
                            onDeleteClick = {
                                selectedAppartementForDelete = appartement
                            }
                        )
                    }
                } else {
                    item {
                        Text(
                            text = if (batimentId != null) "Pas d'appartement pour ce bâtiment" else "Aucun appartement trouvé",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 1.dp)
                                .padding(16.dp),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

        Column(modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)) {
            FloatingActionButton(onClick = { onAddAppartementClick?.invoke() }) {
                Icon(Icons.Default.Add, contentDescription = "Ajouter un appartement")
            }
            Spacer(modifier = Modifier.height(16.dp))
            FloatingActionButton(onClick = { modeSuppressionActive = true }) {
                Icon(Icons.Default.Delete, contentDescription = "Activer la suppression")
            }
        }

        selectedAppartementForDelete?.let { appartement ->
            AlertDialog(
                onDismissRequest = {
                    selectedAppartementForDelete = null
                    modeSuppressionActive = false
                },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.deleteAppartementById(appartement.id)
                        selectedAppartementForDelete = null
                        modeSuppressionActive = false
                    }) {
                        Text("Confirmer")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        selectedAppartementForDelete = null
                        modeSuppressionActive = false
                    }) {
                        Text("Annuler")
                    }
                },
                title = { Text("Supprimer cet appartement ?") },
                text = { Text("Cette action est irréversible.") }
            )
        }
    }
}