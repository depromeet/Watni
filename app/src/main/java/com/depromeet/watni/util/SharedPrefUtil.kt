package com.depromeet.watni.util

import android.app.Activity
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.core.content.edit
import com.depromeet.watni.MainApplication
import com.depromeet.watni.model.request.User
import com.depromeet.watni.model.request.UserLogin
import com.depromeet.watni.model.response.IssueTokenResponse
import com.google.gson.Gson
import java.security.KeyPairGenerator


object SharedPrefUtil {
    private const val KEY_USER_INFO = "key_user_info"
    private const val KEY_USER_LOGIN_INFO = "key_user_login_info"
    private const val KEY_IS_FIRST_LAUNCH = "key_is_first_launch"
    private const val KEY_ACCESS_TOKEN = "key_access_token"
    private const val KEY_REFRESH_TOKEN = "key_refresh_token"
    private const val ALIAS = "encryption_alias"

    private val parameterSpec: KeyGenParameterSpec = KeyGenParameterSpec.Builder(
        ALIAS,
        KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_VERIFY
    ).run {
        setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
        build()
    }

    private val keyPairGenerator: KeyPairGenerator = KeyPairGenerator.getInstance(
        KeyProperties.KEY_ALGORITHM_EC,
        "AndroidKeyStore"
    ).also {
        it.initialize(parameterSpec)
    }

    val kp = keyPairGenerator.generateKeyPair()

    private val sharedPref: SharedPreferences by lazy {
        MainApplication.getContext().getSharedPreferences(SharedPrefUtil::class.java.name, Activity.MODE_PRIVATE)
    }

    fun isFirstLaunch(): Boolean = sharedPref.getBoolean(KEY_IS_FIRST_LAUNCH, true)

    fun setSplashIsLoaded() {
        sharedPref.edit { putBoolean(KEY_IS_FIRST_LAUNCH, false) }
    }

    fun saveUserInfo(user: User) {
        sharedPref.edit { putString(KEY_USER_INFO, Gson().toJson(user)) }
    }

    fun saveUserLoginInfo(userLogin: UserLogin) {
        sharedPref.edit { putString(KEY_USER_LOGIN_INFO, Gson().toJson(userLogin)) }
    }

    fun isLoggedIn() = getUserLoginInfo() != null

    fun getUserInfo(): User? = Gson().fromJson<User>(
        sharedPref.getString(KEY_USER_INFO, ""), User::class.java
    )

    fun getUserLoginInfo(): UserLogin? = Gson().fromJson<UserLogin>(
        sharedPref.getString(KEY_USER_LOGIN_INFO, ""), UserLogin::class.java
    )

    fun saveToken(token: IssueTokenResponse) {
        sharedPref.edit {
            putString(KEY_ACCESS_TOKEN, token.accessToken)
            putString(KEY_REFRESH_TOKEN, token.refreshToken)
        }
    }

    fun getAccessToken() = sharedPref.getString(KEY_ACCESS_TOKEN, null)

    fun getRefreshToken() = sharedPref.getString(KEY_REFRESH_TOKEN, null)

    fun clearAll() {
        sharedPref.edit { clear() }
    }
}