package bts.sio.azurimmo.views.Batiment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import bts.sio.azurimmo.model.Batiment

@Composable
fun BatimentCard(batiment: Batiment, onClick: (Int) -> Unit) { // Notez l'annotation @Composable
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onClick(batiment.id)},
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row {
                    Text(
                        text = "Adresse: ",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = batiment.adresse,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row {
                    Text(
                        text = "Ville: ",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = batiment.ville,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
//                Text(text = batiment.adresse, style = MaterialTheme.typography.bodyLarge)
//                Text(text = batiment.ville, style = MaterialTheme.typography.bodyMedium)
           }
        }
}

