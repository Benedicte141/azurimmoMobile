package bts.sio.azurimmo.views.Appartement

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewsmodel.appartement.AppartementViewModel


// Fonction Composable pour afficher la liste des bâtiments
@Composable
fun AppartementList() {
// Récupérer le ViewModel dans le composable avec viewModel()
    val viewModel: AppartementViewModel = viewModel()
// Observer les données des appartements via le ViewModel
    val appartements = viewModel.appartements.value
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(appartements) { appartement ->
            AppartementCard(appartement = appartement) // Appel de la fonction AppartementCard
        }
    }
}