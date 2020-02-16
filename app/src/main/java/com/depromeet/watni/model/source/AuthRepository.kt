package com.depromeet.watni.model.source

import android.util.Log
import com.depromeet.watni.model.request.IssueToken
import com.depromeet.watni.model.request.UserJoin
import com.depromeet.watni.network.RetrofitBuilder
import com.depromeet.watni.network.SignApi
import com.depromeet.watni.network.retrofitCallback
import com.depromeet.watni.util.SharedPrefUtil

object AuthRepository {
    private val sign: SignApi = RetrofitBuilder.service
    private val TAG = AuthRepository::class.java.simpleName

    fun issueToken(
        userJoin: UserJoin
    ) {
        val bodyData = IssueToken(userJoin.email, userJoin.password)
        sign.issueToken(bodyData).enqueue(retrofitCallback { response, throwable ->
            response?.let {
                if (response.isSuccessful) {
                    SharedPrefUtil.saveAccessToken(response.body()?.accessToken ?: "")
                    SharedPrefUtil.saveRefreshToken(response.body()?.refreshToken ?: "")
                }
            }
            throwable?.let {
                Log.d(TAG, it.message ?: "issueToken")
            }
        })
    }
}