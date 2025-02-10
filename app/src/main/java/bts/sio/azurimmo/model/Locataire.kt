package bts.sio.azurimmo.model

import java.time.LocalDate

data class Locataire(
    val id: Int,
    val nom: String,
    val prenom: String,
    val dateNaissance: String,
    val lieuNaissance: String,
)

