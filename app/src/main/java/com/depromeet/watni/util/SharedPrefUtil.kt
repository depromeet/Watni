package com.depromeet.watni.util

import android.app.Activity
import android.content.SharedPreferences
import androidx.core.content.edit
import com.depromeet.watni.MainApplication
import com.depromeet.watni.model.request.UserLogin
import com.google.gson.Gson


object SharedPrefUtil {
    private const val KEY_USER_INFO = "key_user_info"
    private const val KEY_IS_FIRST_LAUNCH = "key_is_first_launch"
    private const val KEY_ACCESS_TOKEN = "key_access_token"
    private const val KEY_REFRESH_TOKEN = "key_refresh_token"

    private val sharedPref: SharedPreferences by lazy {
        MainApplication.getContext().getSharedPreferences(SharedPrefUtil::class.java.name, Activity.MODE_PRIVATE)
    }

    fun isFirstLaunch(): Boolean {
        val isFirst = sharedPref.getBoolean(KEY_IS_FIRST_LAUNCH, true)
        if (isFirst) {
            sharedPref.edit { putBoolean(KEY_IS_FIRST_LAUNCH, false) }
        }
        return isFirst
    }

    fun saveUserInfo(user: UserLogin) {
        sharedPref.edit { putString(KEY_USER_INFO, Gson().toJson(user)) }
    }

    fun isLoggedIn() = getUserInfo() != null

    fun getUserInfo(): UserLogin? = Gson().fromJson<UserLogin>(
        sharedPref.getString(KEY_USER_INFO, ""), UserLogin::class.java
    )

    fun saveAccessToken(token: String) {
        sharedPref.edit { putString(KEY_ACCESS_TOKEN, token) }
    }

    fun getAccessToken() = sharedPref.getString(KEY_ACCESS_TOKEN, null)

    fun saveRefreshToken(token: String) {
        sharedPref.edit { putString(KEY_REFRESH_TOKEN, token) }
    }

    fun getRefreshToken() = sharedPref.getString(KEY_REFRESH_TOKEN, null)

    fun clearAll() {
        sharedPref.edit { clear() }
    }
}