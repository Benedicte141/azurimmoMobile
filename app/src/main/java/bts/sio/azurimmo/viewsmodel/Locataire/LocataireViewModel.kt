package bts.sio.azurimmo.viewsmodel.Locataire

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import bts.sio.azurimmo.model.Locataire
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import bts.sio.azurimmo.model.Contrat
import kotlinx.coroutines.launch


class LocataireViewModel : ViewModel(){
    // Liste mutable des locataires
    private val _locataires = mutableStateOf<List<Locataire>>(emptyList())
    val locataires: State<List<Locataire>> = _locataires
    private val _isLoading = mutableStateOf(false)

    val isLoading: State<Boolean> = _isLoading
    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage
    init {
// Simuler un chargement de données initiales
        getLocataires()
    }
    private fun getLocataires() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.getLocataires()
                _locataires.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
                println("pas de chargement")
            }
        }
    }

    fun loadAllLocataires() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.getLocataires()
                _locataires.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addLocataire(locataire: Locataire) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                Log.d("LOCATAIRE_API", "Envoi du locataire : $locataire")

                // Envoi à l'API (ici, un POST)
                val response = RetrofitInstance.api.addLocataire(locataire)
                if (response.isSuccessful) {
                    Log.d("LOCATAIRE_API", "Ajout réussi ✅")
                    // Ajout réussi, on met à jour la liste des locataires
                    getLocataires() // Recharge les locataires pour inclure le nouveau
                } else {
                    Log.e("LOCATAIRE_API", "Erreur API ❌ : ${response.code()} - ${response.message()}")
                    _errorMessage.value = "Erreur lors de l'ajout du locataire : ${response.message()}"
                }
            } catch (e: Exception) {
                Log.e("LOCATAIRE_API", "Exception réseau 🚨 : ${e.message}")
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
            }

        }
    }
}
