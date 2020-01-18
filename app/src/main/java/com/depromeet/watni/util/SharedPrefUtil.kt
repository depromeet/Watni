package com.depromeet.watni.util

import android.app.Activity
import android.content.SharedPreferences
import androidx.core.content.edit
import com.depromeet.watni.MainApplication

object SharedPrefUtil {
    private val sharedPref: SharedPreferences by lazy {
        MainApplication.getContext().getSharedPreferences(
            SharedPrefUtil::class.java.name, Activity.MODE_PRIVATE
        )
    }

    fun clearAll() {
        sharedPref.edit { clear() }
    }
}