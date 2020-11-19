package com.dgm.pruebagft.repository.local.prefs

interface PreferenceHelper {

    fun getAccessToken(): String?
    fun setAccessToken(accessToken: String?)

    fun getUserId(): String?
    fun setUserId(id: String?)

}