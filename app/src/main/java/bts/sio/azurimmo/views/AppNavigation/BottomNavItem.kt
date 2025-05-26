package bts.sio.azurimmo.views.navigation

import androidx.annotation.DrawableRes
import bts.sio.azurimmo.R

sealed class BottomNavItem(val route: String, val label: String, @DrawableRes val icon: Int) {
    object Batiment : BottomNavItem("batiment_list", "Bâtiments", R.drawable.ic_building)
    object Appartement : BottomNavItem("appartement_list", "Appartements", R.drawable.ic_apartment)
    object AddBatiment : BottomNavItem("add_batiment", "Ajouter", R.drawable.ic_add)
    object Contrat : BottomNavItem("contrat_list_global", "Contrats", R.drawable.ic_contrat)

}
