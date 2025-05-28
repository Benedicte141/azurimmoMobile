package bts.sio.azurimmo.viewsmodel.contrat

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import bts.sio.azurimmo.model.Contrat
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import kotlinx.coroutines.launch


class ContratViewModel : ViewModel(){
    // Liste mutable des Contrats
    private val _contrats = mutableStateOf<List<Contrat>>(emptyList())
    val contrats: State<List<Contrat>> = _contrats
    private val _isLoading = mutableStateOf(false)

    val isLoading: State<Boolean> = _isLoading
    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage
    init {

   // Simuler un chargement de données initiales
        getContrats()
    }
    fun getContrats() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.getContrats()
                _contrats.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
                println("pas de chargement")
            }
        }
    }

    // Fonction pour récupérer tous les contrats

    fun loadAllContrats() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.getContrats()
                _contrats.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addContrat(contrat: Contrat) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                Log.d("CONTRAT_API", "Envoi du contrat : $contrat")

                // Envoi à l'API (ici, un POST)
                val response = RetrofitInstance.api.addContrat(contrat)
                if (response.isSuccessful) {
                    Log.d("CONTRAT_API", "Ajout réussi ✅")
                    // Ajout réussi, on met à jour la liste des contrats
                    getContrats() // Recharge les apartements pour inclure le nouveau
                } else {
                    Log.e("CONTRAT_API", "Erreur API ❌ : ${response.code()} - ${response.message()}")
                    _errorMessage.value = "Erreur lors de l'ajout du contrat : ${response.message()}"
                }
            } catch (e: Exception) {
                Log.e("CONTRAT_API", "Exception réseau 🚨 : ${e.message}")
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
            }

        }
    }
}