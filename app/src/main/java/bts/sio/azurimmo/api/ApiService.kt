package bts.sio.azurimmo.api
import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.model.Contrat
import bts.sio.azurimmo.model.Entreprise
import bts.sio.azurimmo.model.Intervention
import bts.sio.azurimmo.model.Locataire
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("api/batiments/")
    suspend fun getBatiments(): List<Batiment>
    @GET("/api/appartements/appartement/batiment/{batimentId}")
    suspend fun getAppartementsByBatimentId(@Path("batimentId") batimentId: Int): List<Appartement>


    @POST("api/batiments/")
    suspend fun addBatiment(@Body batiment: Batiment): Response<Batiment>


    @GET("/api/batiments/batiment/{id}")
    suspend fun getBatiment(@Path("id") batimentId: Int): Batiment
    @GET("api/appartements/")
    suspend fun getAppartements(): List<Appartement>
    @GET("api/appartements/appartements/{id}")
    suspend fun getAppartementById(@Path("id") id: Int): Appartement
    @GET("api/appartements/appartements/{id}/hasContrat")
    suspend fun hasContrat(@Path("id") id: Int): Boolean




    @POST("api/appartements/")
    suspend fun addAppartement(@Body appartement: Appartement): Response<Appartement>
    @PUT("api/appartements/appartements/{id}")
    suspend fun updateAppartement(@Path("id") id: Int, @Body appartement: Appartement): Response<Appartement>
    @DELETE("api/appartements/appartements/{id}")
    suspend fun deleteAppartement(@Path("id") id: Int): retrofit2.Response<Unit>


    @GET("api/contrats/")
    suspend fun getContrats(): List<Contrat>
    @POST("api/contrats/")
    suspend fun addContrat(@Body contrat: Contrat): Response<Contrat>



    @GET("api/interventions/")
    suspend fun getInterventions(): List<Intervention>

    @GET("api/entreprises/")
    suspend fun getEntreprises(): List<Entreprise>

    @GET("api/locataires/")
    suspend fun getLocataires(): List<Locataire>
    @POST("api/locataires/")
    suspend fun addLocataire(@Body locataire: Locataire): Response<Locataire>


}