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

        composable("add_batiment") {
            BatimentAdd(onBatimentAdd = {
                navController.popBackStack() // La navigation est gérée ici
            })
        }


        composable("appartement_list") {
            AppartementList(
                onAddAppartementClick = {
                    navController.navigate("appartementAdd")
                },
        navController = navController
            )
        }

        composable("appartement_list") {
            AppartementList(navController = navController)
        }




        // Route pour ajouter un appartement

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

        composable("add_appartement/{batimentId}",
            arguments = listOf(navArgument("batimentId") { type = NavType.IntType })
        )
        { backStackEntry ->
            val batimentId = backStackEntry.arguments?.getInt("batimentId")
            println("Ouverture de add_appartement avec batimentId = $batimentId")
            if (batimentId != null) {
                AppartementAdd( onAddAppartement = { navController.popBackStack()},
                    batimentId = batimentId
                )
            } else {
                Text("Erreur : Identifiant de bâtiment manquant")
            }
        }



        composable("contrat_list") {
            ContratList(navController = navController)
        }

        composable("contratAdd") {
            ContratAdd(onContratAdd = { navController.popBackStack() })
        }


        composable("locataire_list") {
            LocataireList(navController)
        }

        composable("locataireAdd") {
            LocataireAdd(onLocataireAdd = { navController.popBackStack() })
        }


    }
}
