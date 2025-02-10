package bts.sio.azurimmo.views.Locataire

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bts.sio.azurimmo.model.Locataire


@Composable
fun LocataireCard(locataire: Locataire) { // Notez l'annotation @Composable
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = locataire.nom, style = MaterialTheme.typography.bodyLarge)
            Text(text = locataire.prenom, style = MaterialTheme.typography.bodyMedium)
            Text(text = locataire.dateNaissance, style = MaterialTheme.typography.bodyMedium)
            Text(text = locataire.lieuNaissance, style = MaterialTheme.typography.bodyMedium)

        }
    }
}
