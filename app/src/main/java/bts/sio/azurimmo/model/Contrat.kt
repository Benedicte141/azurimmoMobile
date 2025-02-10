package bts.sio.azurimmo.model


data class Contrat (
    val id: Int,
    val dateEntree: String,
    val dateSortie: String,
    val montantLoyer: Double,
    val montantCharges: Double,
    val statut: String,
    val appartement: Appartement,
    val locataire: Locataire

)