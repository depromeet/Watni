package com.depromeet.watni.model.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.depromeet.watni.model.request.RefreshToken
import com.depromeet.watni.model.request.User
import com.depromeet.watni.model.request.UserJoin
import com.depromeet.watni.model.request.UserLogin
import com.depromeet.watni.network.*
import com.depromeet.watni.util.NetworkUtil
import com.depromeet.watni.util.SharedPrefUtil

object SignRepository : SignDataSource {
    private val tag = SignRepository::class.java.simpleName
    private val service: ServiceApi = RetrofitBuilder.service

    override fun userJoin(
        user: UserJoin,
        success: () -> Unit,
        failed: (String, String?) -> Unit
    ) {
        service.userJoin(user).enqueue(retrofitCallback { response, throwable ->
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
        service.issueToken(user, HeaderProvider.getIssueTokenHeader()).enqueue(retrofitCallback { response, throwable ->
            response?.let {
                if (response.isSuccessful) {
                    SharedPrefUtil.saveUserLoginInfo(user)
                    SharedPrefUtil.saveAccessToken(response.body()?.accessToken ?: "")
                    SharedPrefUtil.saveRefreshToken(response.body()?.refreshToken ?: "")
                    run(success)
                } else {
                    failed(tag, LOGIN_FAILED_MSG)
                }
            }
            throwable?.let {
                Log.e(tag, it.message ?: "issueToken error")
                failed(tag, NETWORK_ERROR_MSG)
            }
        })
    }

    override fun refreshToken(
        refreshToken: RefreshToken,
        success: () -> Unit,
        failed: (String, String?) -> Unit
    ) {
        service.refreshToken(refreshToken, HeaderProvider.getIssueTokenHeader())
            .enqueue(retrofitCallback { response, throwable ->
                response?.let {
                    SharedPrefUtil.saveAccessToken(response.body()?.accessToken ?: "")
                    SharedPrefUtil.saveRefreshToken(response.body()?.refreshToken ?: "")
                    run(success)
                }
                throwable?.let {
                    Log.e(tag, it.message ?: "refreshToken error")
                    failed(tag, NETWORK_ERROR_MSG)
                }
            })
    }

    fun getUserInfo(): MutableLiveData<User> {
        val result = MutableLiveData<User>()
        getUserInfo(
            success = {
                result.value = it
            }, failed = { _, _ ->
                result.value = null
            })
        return result
    }

    override fun getUserInfo(
        success: (user: User) -> Unit,
        failed: (String, String?) -> Unit
    ) {
        service.getUserInfo().enqueue(retrofitCallback { response, throwable ->
            response?.let {
                if (response.isSuccessful && response.body() != null) {
                    SharedPrefUtil.saveUserInfo(response.body()!!)
                    success(response.body()!!)
                } else {
                    failed(tag, LOAD_USER_INFO_FAIL_MSG)
                }
            }
            throwable?.let {
                Log.e(tag, it.message ?: "getUserInfo error")
                failed(tag, NETWORK_ERROR_MSG)
            }
        })
    }

    override fun checkAuthState(
        success: () -> Unit,
        failed: (String, String?) -> Unit
    ) {
        if (SharedPrefUtil.getUserLoginInfo() == null) {
            failed(tag, "No userLoginInfo")
            return
        }

        userLogin(SharedPrefUtil.getUserLoginInfo()!!, {
            success()
        }, { _, _ ->
            refreshToken(
                RefreshToken(SharedPrefUtil.getRefreshToken()!!, GRANT_TYPE_TOKEN),
                success = { success() },
                failed = { tag, msg ->
                    Log.e(tag, msg ?: "checkAuthState error")
                    failed(tag, msg)
                }
            )
        })
    }
}