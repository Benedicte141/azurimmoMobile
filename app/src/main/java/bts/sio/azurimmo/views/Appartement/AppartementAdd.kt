package bts.sio.azurimmo.views.Appartement

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.viewsmodel.appartement.AppartementViewModel
import bts.sio.azurimmo.viewsmodel.batiment.BatimentViewModel


@Composable
fun AppartementAdd(onAddAppartement: (Appartement) -> Unit, batimentId: Int? = null){
    val viewModel: AppartementViewModel = viewModel()
    val batimentViewModel: BatimentViewModel = viewModel()

    val batiments by batimentViewModel.batiments
    var selectedBatiment by remember { mutableStateOf<Batiment?>(null) }
    var batimentMenuExpanded by remember { mutableStateOf(false) }

    var description by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var surface by remember { mutableStateOf("") }
    var nbrePieces by remember { mutableStateOf("") }

// Charger la liste des bâtiments au chargement de l’écran
    LaunchedEffect(Unit) {
        batimentViewModel.getBatiments()

        if (batimentId != null) {
            val selected = batiments.firstOrNull { it.id == batimentId }
            if (selected != null) selectedBatiment = selected
        }

    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
// Menu déroulant pour sélectionner un bâtiment
        Text("Sélectionnez un bâtiment :")
        Box {
            TextField(
                value = selectedBatiment?.adresse ?: "Choisir un bâtiment",
                onValueChange = {},
                readOnly = true,
                label = { Text("Bâtiment") },
                trailingIcon = {
                    IconButton(onClick = { batimentMenuExpanded = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "expand")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            DropdownMenu(
                expanded = batimentMenuExpanded,
                onDismissRequest = { batimentMenuExpanded = false }
            ) {
                batiments.forEach { batiment ->
                    DropdownMenuItem(
                        text = { Text("${batiment.adresse}, ${batiment.ville}") },
                        onClick = {
                            selectedBatiment = batiment
                            batimentMenuExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = numero,
            onValueChange = { numero = it },
            label = { Text("Numéro") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = surface,
            onValueChange = { surface = it },
            label = { Text("Surface") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = nbrePieces,
            onValueChange = { nbrePieces = it },
            label = { Text("Nombre de pièces") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (selectedBatiment != null) {
                    val appartement = Appartement(
                        id = 0,
                        numero = numero,
                        description = description,
                        surface = surface.toDouble(),
                        nbrePieces = nbrePieces.toInt(),
                        batiment = selectedBatiment!!
                    )
                    viewModel.addAppartement(appartement)
                    onAddAppartement(appartement)
                } else {
                    println("Veuillez sélectionner un bâtiment.")
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Ajouter l'appartement")
        }
    }
}