package bts.sio.azurimmo.api
import bts.sio.azurimmo.model.Batiment
import retrofit2.http.GET

interface ApiService {
    @GET("api/batiments/")
    suspend fun getBatiments(): List<Batiment>
}