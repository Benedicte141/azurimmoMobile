package bts.sio.azurimmo.viewsmodel.entreprise

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import bts.sio.azurimmo.model.Entreprise
import kotlinx.coroutines.launch

class EntrepriseViewModel : ViewModel(){
    // Liste mutable des entreprises
    private val _entreprises = mutableStateOf<List<Entreprise>>(emptyList())
    val entreprises: State<List<Entreprise>> = _entreprises
    private val _isLoading = mutableStateOf(false)

    val isLoading: State<Boolean> = _isLoading
    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage
    init {
// Simuler un chargement de données initiales
        getEntreprises()
    }
    private fun getEntreprises() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.getEntreprises()
                _entreprises.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
                println("pas de chargement")
            }
        }
    }
}