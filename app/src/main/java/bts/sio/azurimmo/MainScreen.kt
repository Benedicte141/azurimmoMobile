package bts.sio.azurimmo

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import bts.sio.azurimmo.views.AppHeader.AppHeader
import bts.sio.azurimmo.views.AppNavigation.AppNavigation
import bts.sio.azurimmo.views.Appartement.AppartementList
import bts.sio.azurimmo.views.Batiment.BatimentList
import bts.sio.azurimmo.views.navigation.BottomNavigationBar

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    Scaffold(
        topBar = { AppHeader() }, // Barre en-tête
        bottomBar = {
            BottomNavigationBar(navController = navController) // Utilisation correcte de navController
        }
    ) { innerPadding ->
        AppNavigation(navController = navController, modifier = Modifier.padding(innerPadding)) // Contenu de la navigation
    }
}





