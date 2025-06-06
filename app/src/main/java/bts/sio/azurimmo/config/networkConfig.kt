package bts.sio.azurimmo.config

import android.util.Log

object NetworkConfig {

    private const val TAG = "BASE_URL"

    fun getBaseUrl(): String {
        // ✏️ Choisir manuellement l'environnement ici :
        val useEmulator = true // ← passe à false pour tester sur le téléphone réel

        val url = if (useEmulator) {
            "http://10.0.2.2:9008/" // URL spéciale pour l’émulateur Android Studio
        } else {
            "http://192.168.137.1:9008/" // IP locale visible par ton téléphone
        }

        Log.d(TAG, "Utilisation de l'URL : $url")
        return url
    }
}
