package com.dgm.pruebagft.repository

import com.auth0.android.jwt.JWT
import com.dgm.pruebagft.repository.local.prefs.AppPreferencesHelper
import com.dgm.pruebagft.repository.remote.api.AppApi
import com.dgm.pruebagft.repository.remote.model.DetailsResponse
import com.dgm.pruebagft.repository.remote.model.User

class AppRepository(
    private val api: AppApi,
    private val loginPrefs: AppPreferencesHelper
) {

    private var token = loginPrefs.getAccessToken()
    private var idCount = loginPrefs.getUserId()


    suspend fun registerAccess(user: User): String {
        val tokenRes = api.registerUser(user)
        return tokenRes.value
    }

    suspend fun loginAccess(email: String, password: String): String? {
        val tokenRes = api.loginUser(email, password)
        token = tokenRes.token
        token.let {
            loginPrefs.setAccessToken(it)
            idCount = JWT(it!!).getClaim("id").asString()
            loginPrefs.setUserId(idCount)
            return JWT(it).getClaim("firstname").asString()
        }
    }

    suspend fun getCatalog() =
        token?.let { api.getCatalog(it) }


    suspend fun addCard(type: String, name: String) {
       api.addCard(token, idCount, type, name).value
    }

    suspend fun getCardInfo() =
        api.getCardInfo(token)

}