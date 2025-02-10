package bts.sio.azurimmo.model

import java.time.LocalDate

data class Contrat (
    val id: Int,
    val dateEntree: String,
    val dateSortie: String,
    val montantLoyer: Double,
    val montantCharges: Double,

)