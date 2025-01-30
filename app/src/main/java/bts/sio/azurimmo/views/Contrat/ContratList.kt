package bts.sio.azurimmo.views.Contrat

import ContratCard
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewsmodel.contrat.ContratViewModel

// Fonction Composable pour afficher la liste des contrats
@Composable
fun ContratList(viewModel: ContratViewModel = viewModel()) {
// Récupérer le ViewModel dans le composable avec viewModel()
    val viewModel: ContratViewModel = viewModel()
// Observer les données des contrats via le ViewModel
    val contrats = viewModel.contrats.value
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(contrats) { contrat ->
            ContratCard(contrat = contrat) // Appel de la fonction ContratCard
        }
    }
}
