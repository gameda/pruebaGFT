package com.dgm.pruebagft.repository.local.prefs

import android.content.Context
import android.content.SharedPreferences


class AppPreferencesHelper(pref: SharedPreferences) : PreferenceHelper {

    private var mPref = pref

    private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
    private val PREF_USER_ID = "PREF_USER_ID"


    override fun getAccessToken() =   mPref.getString(PREF_KEY_ACCESS_TOKEN, null);

    override fun setAccessToken(accessToken: String?) {
        mPref.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()
    }


    override fun getUserId() =   mPref.getString(PREF_USER_ID, null);

    override fun setUserId(id: String?) {
        mPref.edit().putString(PREF_USER_ID, id).apply()
    }

}