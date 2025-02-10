package bts.sio.azurimmo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import bts.sio.azurimmo.ui.theme.AzurimmoTheme
import bts.sio.azurimmo.views.Appartement.AppartementList
import bts.sio.azurimmo.views.Batiment.BatimentList
import bts.sio.azurimmo.views.Contrat.ContratList
import bts.sio.azurimmo.views.Entreprise.EntrepriseList
import bts.sio.azurimmo.views.Intervention.InterventionList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AzurimmoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
//                BatimentList()
//               AppartementList()
//                ContratList()
                InterventionList()
 //               EntrepriseList()

            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

// Fonction pour prévisualiser l'interface
@Preview(showBackground = true)
@Composable
fun PreviewBatimentList() {
    BatimentList()
}

// Fonction pour prévisualiser l'interface
@Preview(showBackground = true)
@Composable
fun PreviewAppartementList() {
    AppartementList()
}

// Fonction pour prévisualiser l'interface
@Preview(showBackground = true)
@Composable
fun PreviewInterventionList() {
    InterventionList()
}

// Fonction pour prévisualiser l'interface
@Preview(showBackground = true)
@Composable
fun PreviewEntrepriseList() {
    EntrepriseList()
}