package bts.sio.azurimmo.views.AppNavigation

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import bts.sio.azurimmo.views.Appartement.AppartementAdd
import bts.sio.azurimmo.views.Appartement.AppartementList
import bts.sio.azurimmo.views.Batiment.BatimentAdd
import bts.sio.azurimmo.views.Batiment.BatimentList
import bts.sio.azurimmo.views.Contrat.ContratAdd
import bts.sio.azurimmo.views.Contrat.ContratList
import bts.sio.azurimmo.views.Locataire.LocataireAdd
import bts.sio.azurimmo.views.Locataire.LocataireList
import bts.sio.azurimmo.viewsmodel.appartement.AppartementViewModel


@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "batiment_list",
        modifier = modifier
    ) {
         //Ecran accueil : Liste Bâtiments
        composable("batiment_list") {
            BatimentList(
                onBatimentClick = { batimentId ->
                    navController.navigate("batiment_appartements_list/$batimentId")
                },
                onAddBatimentClick = {
                    navController.navigate("add_batiment")
                },
                navController= navController,
            )
        }

        // ***************           BATIMENTS       **************************************
        // Liste détail des appartements par IdBatiment
        composable(
            route = "batiment_appartements_list/{batimentId}",
            arguments = listOf(navArgument("batimentId") { type = NavType.IntType })
        ) { backStackEntry ->
            val batimentId = backStackEntry.arguments?.getInt("batimentId")
            if (batimentId != null) {
                AppartementList(
                    batimentId = batimentId,
                    onAddAppartementClick =  {
                    navController.navigate("add_appartement/$batimentId")
                },
            navController = navController
                )
            } else {
                Text("Erreur : Identifiant de bâtiment manquant")
            }
        }

        // Route Icône Ajouter un Batiment depuis la liste globale
        composable("add_batiment") {
            BatimentAdd(onBatimentAdd = {
                navController.popBackStack() // La navigation est gérée ici
            })
        }



        // ***************           APPARTEMENTS       *********************************
        // Route liste globale via l'icône Appartements
        composable("appartement_list") {
            AppartementList(navController = navController)
        }


        // Route liste globale : ajouter un appartement à partir d'un IdBatiment ou
        // de la liste globale Appartements

        composable("appartementAdd") {
            val appartementViewModel: AppartementViewModel = viewModel()

            AppartementAdd(
                onAddAppartement = {
                    appartementViewModel.loadAllAppartements() // 🔁 Rafraîchir
                    navController.popBackStack()
                },
                batimentId = null
            )
        }

        // ***************           CONTRATS       *********************************
        // Route liste globale via l'icône Contrats
        composable("contrat_list") {
            ContratList(navController = navController)
        }
        // Route Icône Ajouter un Contrat depuis la liste globale
        composable("contratAdd") {
            ContratAdd(onContratAdd = { navController.popBackStack() })
        }

        // ***************           LOCATAIRES       *********************************
        // Route liste globale via l'icône Locataires
        composable("locataire_list") {
            LocataireList(navController)
        }

        // Route Icône Ajouter un Locataire depuis la liste globale
        composable("locataireAdd") {
            LocataireAdd(onLocataireAdd = { navController.popBackStack() })
        }


    }
}
