package bts.sio.azurimmo.views.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Log.d("BOTTOM_NAV", "Route actuelle : $currentRoute")

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary
    ) {
        listOf(
            BottomNavItem.Batiment,
            BottomNavItem.Appartement,
            BottomNavItem.Contrat,
            BottomNavItem.Locataire,
          //  BottomNavItem.AddBatiment

        ).forEach { item ->
            NavigationBarItem(
                icon = {
                    Image(painter = painterResource(id = item.icon), contentDescription = item.label)
                },
                label = { androidx.compose.material3.Text(item.label) },
                selected = false, // Ajoute une logique de sélection si nécessaire
                onClick = {
                    navController.navigate(item.route) {
                        // Évite d'empiler plusieurs fois la même destination
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
