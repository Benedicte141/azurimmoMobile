package bts.sio.azurimmo.model

import java.time.LocalDate

data class Intervention(
    val id: Int,
    val description: String,
    val typeIntervention: String,
    val	dateIntervention: String,
    val	appartement : Appartement,
    val entreprise: Entreprise

)
