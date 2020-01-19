package com.depromeet.watni.util

import android.app.Activity
import android.content.SharedPreferences
import androidx.core.content.edit
import com.depromeet.watni.MainApplication
import com.depromeet.watni.model.request.User
import com.google.gson.Gson


object SharedPrefUtil {
    private const val KEY_USER_INFO = "key_user_info"
    private const val KEY_IS_FIRST_LAUNCH = "key_is_first_launch"

    private val sharedPref: SharedPreferences by lazy {
        MainApplication.getContext().getSharedPreferences(
            SharedPrefUtil::class.java.name, Activity.MODE_PRIVATE
        )
    }

    fun isFirstLaunch(): Boolean {
        val isFirst = sharedPref.getBoolean(KEY_IS_FIRST_LAUNCH, true)
        if (isFirst) {
            sharedPref.edit { putBoolean(KEY_IS_FIRST_LAUNCH, false) }
        }
        return isFirst
    }

    fun saveUserInfo(user: User) {
        sharedPref.edit { putString(KEY_USER_INFO, Gson().toJson(user)) }
    }

    fun getUserInfo(): User? = Gson().fromJson<User>(
        sharedPref.getString(KEY_USER_INFO, ""), User::class.java
    )

    fun clearAll() {
        sharedPref.edit { clear() }
    }
}