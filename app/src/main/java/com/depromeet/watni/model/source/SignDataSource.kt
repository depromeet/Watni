package com.depromeet.watni.model.source

import com.depromeet.watni.model.request.RefreshToken
import com.depromeet.watni.model.request.User
import com.depromeet.watni.model.request.UserJoin
import com.depromeet.watni.model.request.UserLogin

interface SignDataSource {

    fun userJoin(user: UserJoin, success: () -> Unit, failed: (String, String?) -> Unit)

    fun userLogin(user: UserLogin, success: () -> Unit, failed: (String, String?) -> Unit)

    fun refreshToken(refreshToken: RefreshToken, success: () -> Unit, failed: (String, String?) -> Unit)

    fun getUserInfo(success: (user: User) -> Unit, failed: (String, String?) -> Unit)

    fun checkAuthState(success: () -> Unit, failed: (String, String?) -> Unit)
}