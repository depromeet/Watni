package com.depromeet.watni.model.source

import android.util.Log
import com.depromeet.watni.model.request.UserJoin
import com.depromeet.watni.network.RetrofitBuilder
import com.depromeet.watni.network.SignApi
import com.depromeet.watni.network.retrofitCallback

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
                if (response.isSuccessful) {
                    success
                } else {
                    failed(TAG, throwable?.message)
                }
            }
            throwable?.let {
                Log.e(TAG, throwable.message, throwable)
                failed(TAG, throwable.message)
            }
        })
    }
}