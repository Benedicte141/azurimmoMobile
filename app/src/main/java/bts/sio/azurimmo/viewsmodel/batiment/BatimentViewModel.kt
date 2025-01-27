package bts.sio.azurimmo.viewsmodel.batiment

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import bts.sio.azurimmo.model.Batiment
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import kotlinx.coroutines.launch
// ViewModel pour gérer les données des bâtiments

class BatimentViewModel : ViewModel() {
    // Liste mutable des bâtiments ==> Méthode Données en dur
//    private val _batiments = mutableStateOf(emptyList<Batiment>())
//    val batiments: State<List<Batiment>> = _batiments
//  init {
// Simuler un chargement de données initiales
//        getBatiments()
//    }
    // Fonction pour simuler le chargement de bâtiments
//    private fun getBatiments() {
//        viewModelScope.launch {
//            _batiments.value = listOf(
//                Batiment(1, "123 Rue Principale", "Nice"),
//                Batiment(2, "456 Avenue des Champs", "Marseille"),
//                Batiment(3, "789 Boulevard Haussmann", "Marseille")
//            )
    // Liste mutable des bâtiments
    private val _batiments = mutableStateOf<List<Batiment>>(emptyList())
    val batiments: State<List<Batiment>> = _batiments
    private val _isLoading = mutableStateOf(false)

    val isLoading: State<Boolean> = _isLoading
    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage
    init {
// Simuler un chargement de données initiales
        getBatiments()
    }
    private fun getBatiments() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.getBatiments()
                _batiments.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
                println("pas de chargement")
            }
        }
    }
}
