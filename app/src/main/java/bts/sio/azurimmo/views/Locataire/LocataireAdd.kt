package bts.sio.azurimmo.views.Locataire

package bts.sio.azurimmo.views.Contrat


import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.model.Contrat
import bts.sio.azurimmo.model.Locataire
import bts.sio.azurimmo.viewsmodel.Locataire.LocataireViewModel
import bts.sio.azurimmo.viewsmodel.appartement.AppartementViewModel
import bts.sio.azurimmo.viewsmodel.contrat.ContratViewModel

@Composable
fun ContratAdd(onContratAdd: () -> Unit) {
    val viewModel: ContratViewModel = viewModel()
    val appartementViewModel: AppartementViewModel = viewModel()
    val appartements by appartementViewModel.appartements
    val locataireViewModel: LocataireViewModel = viewModel()
    val locataires by locataireViewModel.locataires
    var selectedLocataire by remember { mutableStateOf<Locataire?>(null) }
    var locataireDropdownExpanded by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var selectedAppartement by remember { mutableStateOf<Appartement?>(null) }
    var dateEntree by remember { mutableStateOf("") }
    var dateSortie by remember { mutableStateOf("") }
    var montantLoyer by remember { mutableStateOf("") }
    var montantCharges by remember { mutableStateOf("") }
    var statut by remember { mutableStateOf("") }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Sélectionnez un appartement :")
        Box {
            TextField(
                value = selectedAppartement?.description ?: "Choisir un appartement",
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Appartement") },
                trailingIcon = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "expand")
                    }
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                appartements.forEach { appartement ->
                    DropdownMenuItem(
                        text = { Text(appartement.description) },
                        onClick = {
                            selectedAppartement = appartement
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("Sélectionnez un locataire :")
        Box {
            TextField(
                value = selectedLocataire?.nom ?: "Choisir un locataire",
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { locataireDropdownExpanded = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "expand")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            DropdownMenu(
                expanded = locataireDropdownExpanded,
                onDismissRequest = { locataireDropdownExpanded = false }
            ) {
                locataires.forEach { locataire ->
                    DropdownMenuItem(
                        text = { Text("${locataire.nom} ${locataire.prenom}") },
                        onClick = {
                            selectedLocataire = locataire
                            locataireDropdownExpanded = false
                        }
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = dateEntree,
            onValueChange = { dateEntree = it },
            label = { Text("Date d'entrée ") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = dateSortie,
            onValueChange = { dateSortie = it },
            label = { Text("Date de sortie") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = montantLoyer,
            onValueChange = { montantLoyer = it },
            label = { Text("Montant du loyer") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = montantCharges,
            onValueChange = { montantCharges = it },
            label = { Text("Montant des charges") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = statut,
            onValueChange = { statut = it },
            label = { Text("Statut") },
            modifier = Modifier.fillMaxWidth()
        )


        Button(
            onClick = {
                if (selectedAppartement != null && selectedLocataire != null) {
                    val contrat = Contrat(
                        id = 0,
                        appartement = selectedAppartement!!,
                        locataire = selectedLocataire!!,
                        dateEntree =  dateEntree,
                        dateSortie = dateSortie,
                        montantLoyer = montantLoyer.toDouble(),
                        montantCharges = montantCharges.toDouble(),
                        statut = statut
                    )
                    Log.d("CONTRAT_ADD", "Contrat à envoyer : $contrat")
                    viewModel.addContrat(contrat)
                    onContratAdd()
                } else {
                    Log.d("CONTRAT_ADD", "App ou locataire non sélectionné")
                    // Action si aucun appartement sélectionné (ex. message d’erreur)
                    println("Veuillez sélectionner un appartement et un locataire.")
                }
            },
            modifier = Modifier.align(Alignment.End)

        ) { Spacer(modifier = Modifier.height(16.dp))
            Text("Ajouter le contrat")
        }
    }
}
