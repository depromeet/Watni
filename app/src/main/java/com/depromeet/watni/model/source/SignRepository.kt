package com.depromeet.watni.model.source

import com.depromeet.watni.model.request.User
import com.depromeet.watni.network.ServiceApi
import com.depromeet.watni.network.retrofitCallback
import com.depromeet.watni.util.SharedPrefUtil

class SignRepository(private val service: ServiceApi) {

    companion object {
        private val TAG = SignRepository::class.java.simpleName
    }

    fun userJoin(
        user: User,
        success: () -> Unit,
        failed: (String, String?) -> Unit
    ) {
        service.userJoin(user).enqueue(retrofitCallback { response, throwable ->
            response?.let {
                if (response.isSuccessful) {
                    SharedPrefUtil.saveUserInfo(user)
                    success
                } else {
                    failed(TAG, throwable?.message)
                }
            }
            throwable?.let {
                failed(TAG, throwable.message)
            }
        })
    }
}