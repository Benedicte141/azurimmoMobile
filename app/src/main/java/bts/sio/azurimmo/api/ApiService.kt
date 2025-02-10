package bts.sio.azurimmo.api
import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.model.Contrat
import bts.sio.azurimmo.model.Entreprise
import bts.sio.azurimmo.model.Intervention
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/batiments/")
    suspend fun getBatiments(): List<Batiment>
    @GET("/api/appartements/appartement/batiment/{batimentId}")

    suspend fun getAppartementsByBatimentId(@Path("batimentId") batimentId: Int): List<Appartement>

    @GET("api/appartements/")
    suspend fun getAppartements(): List<Appartement>

    @GET("api/contrats/")
    suspend fun getContrats(): List<Contrat>

    @GET("api/interventions/")
    suspend fun getInterventions(): List<Intervention>

    @GET("api/entreprises/")
    suspend fun getEntreprises(): List<Entreprise>
}