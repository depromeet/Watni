package com.depromeet.watni.model.source

import android.util.Log
import com.depromeet.watni.model.request.UserJoin
import com.depromeet.watni.model.request.UserLogin
import com.depromeet.watni.network.*
import com.depromeet.watni.util.NetworkUtil
import com.depromeet.watni.util.SharedPrefUtil

class SignRepository : SignDataSource {
    private val tag = SignRepository::class.java.simpleName
    private val sign: SignApi = RetrofitBuilder.service

    override fun userJoin(
        user: UserJoin,
        success: () -> Unit,
        failed: (String, String?) -> Unit
    ) {
        sign.userJoin(user).enqueue(retrofitCallback { response, throwable ->
            response?.let {
                if (it.isSuccessful) {
                    run(success)
                } else {
                    val errorDescription = NetworkUtil.parseErrorDescription(it.errorBody())
                    val msg = if (errorDescription == ALREADY_USED_EMAIL_DESC) ALREADY_USED_EMAIL_MSG else NETWORK_ERROR_MSG
                    failed(tag, msg)
                }
            }
            throwable?.let {
                Log.e(tag, throwable.message, throwable)
                failed(tag, NETWORK_ERROR_MSG)
            }
        })
    }

    override fun userLogin(
        user: UserLogin,
        success: () -> Unit,
        failed: (String, String?) -> Unit
    ) {
        sign.issueToken(user, HeaderProvider.createIssueTokenHeader()).enqueue(retrofitCallback { response, throwable ->
            response?.let {
                if (response.isSuccessful) {
                    run(success)
                    SharedPrefUtil.saveAccessToken(response.body()?.accessToken ?: "")
                    SharedPrefUtil.saveRefreshToken(response.body()?.refreshToken ?: "")
                } else {
                    failed(tag, LOGIN_FAILED_MSG)
                }
            }
            throwable?.let {
                Log.d(tag, it.message ?: "issueToken error")
            }
        })
    }
}