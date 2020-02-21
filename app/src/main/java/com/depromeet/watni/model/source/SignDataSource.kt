package com.depromeet.watni.model.source

import com.depromeet.watni.model.request.UserJoin
import com.depromeet.watni.model.request.UserLogin

interface SignDataSource {

    fun userJoin(
        user: UserJoin,
        success: () -> Unit,
        failed: (String, String?) -> Unit
    )

    fun userLogin(
        user: UserLogin,
        success: () -> Unit,
        failed: (String, String?) -> Unit
    )
}