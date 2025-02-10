package bts.sio.azurimmo.viewsmodel.intervention

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import bts.sio.azurimmo.model.Intervention
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import kotlinx.coroutines.launch

class InterventionViewModel : ViewModel() {
        // Liste mutable des Interventions
        private val _interventions = mutableStateOf<List<Intervention>>(emptyList())
        val interventions: State<List<Intervention>> = _interventions
        private val _isLoading = mutableStateOf(false)

        val isLoading: State<Boolean> = _isLoading
        private val _errorMessage = mutableStateOf<String?>(null)
        val errorMessage: State<String?> = _errorMessage
        init {
// Simuler un chargement de données initiales
            getInterventions()
        }
        private fun getInterventions() {
            viewModelScope.launch {
                _isLoading.value = true
                try {
                    val response = RetrofitInstance.api.getInterventions()
                    _interventions.value = response
                } catch (e: Exception) {
                    _errorMessage.value = "Erreur : ${e.message}"
                } finally {
                    _isLoading.value = false
                    println("pas de chargement")
                }
            }
        }
    }
