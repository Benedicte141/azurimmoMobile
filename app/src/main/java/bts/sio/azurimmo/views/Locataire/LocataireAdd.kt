package bts.sio.azurimmo.views.Locataire

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
import bts.sio.azurimmo.model.Contrat
import bts.sio.azurimmo.model.Locataire
import bts.sio.azurimmo.viewsmodel.Locataire.LocataireViewModel
import bts.sio.azurimmo.viewsmodel.contrat.ContratViewModel

@Composable
fun LocataireAdd(onLocataireAdd: () -> Unit) {
    val viewModel: LocataireViewModel = viewModel()

    var nom by remember { mutableStateOf("") }
    var prenom by remember { mutableStateOf("") }
    var dateNaissance by remember { mutableStateOf("") }
    var lieuNaissance by remember { mutableStateOf("") }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = nom,
            onValueChange = { nom = it },
            label = { Text("nom ") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = prenom,
            onValueChange = { prenom = it },
            label = { Text("Prénom") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = dateNaissance,
            onValueChange = { dateNaissance = it },
            label = { Text("Date de naissance") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = lieuNaissance,
            onValueChange = { lieuNaissance = it },
            label = { Text("Lieu de naissance") },
            modifier = Modifier.fillMaxWidth()
       )


        Button(
            onClick = {

                    val locataire = Locataire(
                        id = 0,
                        nom = nom,
                        prenom = prenom,
                        dateNaissance = dateNaissance,
                        lieuNaissance = lieuNaissance
                    )
                Log.d("LOCATAIRE_ADD", "Locataire à envoyer : $locataire")
                    viewModel.addLocataire(locataire)
                    onLocataireAdd()
                },
            modifier = Modifier.align(Alignment.End)

        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Ajouter le locataire")
        }
    }
}
