package com.depromeet.watni.model.source

import android.util.Log
import com.depromeet.watni.model.request.UserJoin
import com.depromeet.watni.model.request.UserLogin
import com.depromeet.watni.network.*
import com.depromeet.watni.util.SharedPrefUtil

object SignRepository {
    private val sign: SignApi = RetrofitBuilder.service
    private val TAG = SignRepository::class.java.simpleName

    fun userJoin(
        user: UserJoin,
        success: () -> Unit,
        failed: (String, String?) -> Unit
    ) {
        sign.userJoin(user).enqueue(retrofitCallback { response, throwable ->
            response?.let {
                when (it.code()) {
                    SUCCESS_CODE -> run(success)
                    FAIL_CODE -> failed(TAG, ALREADY_USED_EMAIL_MSG)
                }
            }
            throwable?.let {
                Log.e(TAG, throwable.message, throwable)
                failed(TAG, NETWORK_ERROR_MSG)
            }
        })
    }

    fun issueToken(
        issueToken: UserLogin
    ) {
        sign.issueToken(issueToken).enqueue(retrofitCallback { response, throwable ->
            response?.let {
                if (response.isSuccessful) {
                    SharedPrefUtil.saveAccessToken(response.body()?.accessToken ?: "")
                    SharedPrefUtil.saveRefreshToken(response.body()?.refreshToken ?: "")
                }
            }
            throwable?.let {
                Log.d(TAG, it.message ?: "issueToken error")
            }
        })
    }
}