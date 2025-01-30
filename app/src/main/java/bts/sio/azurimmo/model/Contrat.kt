package bts.sio.azurimmo.model

import java.time.LocalDate

data class Contrat (
    val id: Int,
    val dateEntree: LocalDate,
    val dateSortie: LocalDate,
    val montantLoyer: Double,
    val montantCharges: Double,

)