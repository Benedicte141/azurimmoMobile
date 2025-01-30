package bts.sio.azurimmo.viewsmodel.appartement

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import bts.sio.azurimmo.model.Appartement
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import kotlinx.coroutines.launch

// ViewModel pour gérer les données des appartements
class AppartementViewModel : ViewModel() {
    // Liste mutable des appartements ===> "Données en dur"
    //private val _appartements = mutableStateOf(emptyList<Appartement>())
    //val appartements: State<List<Appartement>> = _appartements
    //init {
    // Simuler un chargement de données initiales
    //    getAppartements()
    //}
    // Fonction pour simuler le chargement de appartements
    //private fun getAppartements() {
    //    viewModelScope.launch {
    //        _appartements.value = listOf(
    //            Appartement(1, 123, 20.0, nbrePieces = 2, description = "Studio"),
    //            Appartement(2, 30, 15.0, nbrePieces = 3, description = "Duplex"),
    //            Appartement(3, 45, 45.0, nbrePieces = 5, description = "Appartement face à la mer")
    //        )
    // Liste mutable des appartements
    private val _appartements = mutableStateOf<List<Appartement>>(emptyList())
    val appartements: State<List<Appartement>> = _appartements
    private val _isLoading = mutableStateOf(false)

    val isLoading: State<Boolean> = _isLoading
    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage
    init {
// Simuler un chargement de données initiales
        getAppartements()
    }
    private fun getAppartements() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.getAppartements()
                _appartements.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
                println("pas de chargement")
            }
        }
    }
}