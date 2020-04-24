package com.depromeet.watni.model.source

import com.depromeet.watni.model.request.User
import com.depromeet.watni.model.request.UserJoin
import com.depromeet.watni.model.request.UserLogin
import com.depromeet.watni.model.response.IssueTokenResponse
import retrofit2.Response

interface SignDataSource {

    fun userJoin(user: UserJoin, success: () -> Unit, failed: (String, String?) -> Unit)

    fun userLogin(user: UserLogin, success: () -> Unit, failed: (String, String?) -> Unit)

    fun refreshToken(): Response<IssueTokenResponse>?

    fun getUserInfo(success: (user: User) -> Unit, failed: (String, String?) -> Unit)

    fun checkAuthState(success: () -> Unit, failed: (String, String?) -> Unit)
}