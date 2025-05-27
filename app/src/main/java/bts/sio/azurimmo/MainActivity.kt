package bts.sio.azurimmo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
//               BatimentList()
//               AppartementList()
//               ContratList()
//               InterventionList()
//               EntrepriseList()

        }
    }
}


//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

//// Fonction pour prévisualiser l'interface
//@Preview(showBackground = true)
//@Composable
//fun PreviewBatimentList() {
//    BatimentList()
//}
//
//// Fonction pour prévisualiser l'interface
//@Preview(showBackground = true)
//@Composable
//fun PreviewAppartementList() {
//    AppartementList()
//}
//
//// Fonction pour prévisualiser l'interface
//@Preview(showBackground = true)
//@Composable
//fun PreviewContratList() {
//    ContratList()
//}
//
//// Fonction pour prévisualiser l'interface
//@Preview(showBackground = true)
//@Composable
//fun PreviewLocataireList() {
//    LocataireList()
//}
//// Fonction pour prévisualiser l'interface
//@Preview(showBackground = true)
//@Composable
//fun PreviewInterventionList() {
//    InterventionList()
//}

// Fonction pour prévisualiser l'interface
//@Preview(showBackground = true)
//@Composable
//fun PreviewEntrepriseList() {
//    EntrepriseList()
//}