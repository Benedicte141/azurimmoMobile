package bts.sio.azurimmo.views.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary
    ) {
        listOf(BottomNavItem.Batiment, BottomNavItem.Appartement, BottomNavItem.AddBatiment).forEach { item ->
            NavigationBarItem(
                icon = {
                    Image(painter = painterResource(id = item.icon), contentDescription = item.label)
                },
                label = { androidx.compose.material3.Text(item.label) },
                selected = false, // Ajoute une logique de sélection si nécessaire
                onClick = {
                    navController.navigate(item.route)
                }
            )
        }
    }
}
