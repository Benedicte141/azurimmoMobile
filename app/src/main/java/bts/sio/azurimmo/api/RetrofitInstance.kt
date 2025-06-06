package bts.sio.azurimmo.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import bts.sio.azurimmo.config.NetworkConfig


object RetrofitInstance {
    /*private const val BASE_URL = "http://10.0.2.2:9008/" */
    /*private const val BASE_URL = "http://192.168.137.1:9008/" */


    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(NetworkConfig.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}