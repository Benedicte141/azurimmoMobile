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
import bts.sio.azurimmo.model.Contrat


@Composable
fun ContratCard(contrat: Contrat) { // Notez l'annotation @Composable
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
            Text(text = contrat.dateEntree.toString(), style = MaterialTheme.typography.bodyLarge)
            Text(text = contrat.dateSortie.toString(), style = MaterialTheme.typography.bodyMedium)
            Text(text = contrat.montantLoyer.toString(), style = MaterialTheme.typography.bodyMedium)
            Text(text = contrat.montantCharges.toString(), style = MaterialTheme.typography.bodyMedium)

        }
    }
}
