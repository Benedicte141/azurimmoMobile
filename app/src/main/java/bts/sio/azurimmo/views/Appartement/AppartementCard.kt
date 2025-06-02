package bts.sio.azurimmo.views.Appartement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import bts.sio.azurimmo.model.Appartement

@Composable
    fun AppartementCard(
    appartement: Appartement,
    modeSuppressionActive: Boolean,
    onDeleteClick: (() -> Unit)? = null,
    onEditClick: (() -> Unit)? = null
    ) {
    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Text(
                    text = "Description : ",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = appartement.description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row {
                Text(
                    text = "Numero : ",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = appartement.numero,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row {
                Text(
                    text = "Surface : ",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = appartement.surface.toString(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Row {
                Text(
                    text = "Nombre de pièces : ",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = appartement.nbrePieces.toString(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Boutons Modifier / Supprimer
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                onEditClick?.let {
                    IconButton(onClick = it) {
                        Icon(Icons.Default.Edit, contentDescription = "Modifier")
                    }
                }

                if (modeSuppressionActive && onDeleteClick != null) {
                    IconButton(onClick = onDeleteClick) {
                        Icon(Icons.Default.Delete, contentDescription = "Supprimer")
                    }
                }
            }
        }
    }
}


