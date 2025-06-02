package bts.sio.azurimmo.views.Appartement

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.viewsmodel.appartement.AppartementViewModel

@Composable
fun AppartementUpdate(
    appartementId: Int,
    navController: NavController,
    viewModel: AppartementViewModel = viewModel()
) {
    val appartement by viewModel.appartement
    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage

    LaunchedEffect(appartementId) {
        viewModel.getAppartementById(appartementId)
    }

    appartement?.let { app ->
        var numero by remember { mutableStateOf(TextFieldValue(app.numero)) }
        var surface by remember { mutableStateOf(TextFieldValue(app.surface.toString())) }
        var nbrePieces by remember { mutableStateOf(TextFieldValue(app.nbrePieces.toString())) }
        var description by remember { mutableStateOf(TextFieldValue(app.description)) }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            TextField(
                value = numero,
                onValueChange = { numero = it },
                label = { Text("Numéro") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = surface,
                onValueChange = { surface = it },
                label = { Text("Surface") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = nbrePieces,
                onValueChange = { nbrePieces = it },
                label = { Text("Nombre de pièces") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val updatedAppartement = app.copy(
                        numero = numero.text,
                        surface = surface.text.toDoubleOrNull() ?: app.surface,
                        nbrePieces = nbrePieces.text.toIntOrNull() ?: app.nbrePieces,
                        description = description.text
                        // 👉 pas de .contrat ici
                    )
                    viewModel.updateAppartement(updatedAppartement)
                    navController.popBackStack()
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Enregistrer")
            }

            if (isLoading) {
                CircularProgressIndicator()
            }

            errorMessage?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    } ?: run {
        if (!isLoading) {
            Text("Appartement introuvable", color = MaterialTheme.colorScheme.error)
        }
    }
}
