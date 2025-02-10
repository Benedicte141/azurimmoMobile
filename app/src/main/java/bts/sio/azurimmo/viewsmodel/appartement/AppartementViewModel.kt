package bts.sio.azurimmo.viewsmodel.appartement

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import bts.sio.azurimmo.model.Appartement
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import kotlinx.coroutines.launch


class AppartementViewModel : ViewModel() {

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
    // Fonction pour récupérer les appartements via un Id de Batiment
    fun getAppartementsByBatimentId(batimentId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.getAppartementsByBatimentId(batimentId)
                _appartements.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

}