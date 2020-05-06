package com.depromeet.watni.network

import com.depromeet.watni.ext.hasUsableBody
import com.depromeet.watni.ext.isInvalidTokenErr
import com.depromeet.watni.model.source.SignRepository
import com.depromeet.watni.util.SharedPrefUtil
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

inline fun <T> retrofitCallback(
    crossinline fn: (Response<T>?, Throwable?) -> Unit
): Callback<T> {
    return object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (!response.isInvalidTokenErr()) {
                runBlocking { SignRepository.refreshToken() }?.also {
                    if (it.hasUsableBody()) {
                        SharedPrefUtil.saveToken(it.body()!!)
                        call.clone().enqueue(this) // retry
                        return
                    }
                }
            }

            fn(response, null)
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            fn(null, t)
        }
    }
}