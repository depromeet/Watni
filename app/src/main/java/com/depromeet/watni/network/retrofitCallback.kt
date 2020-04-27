package com.depromeet.watni.network

import com.depromeet.watni.model.source.SignRepository
import com.depromeet.watni.util.NetworkUtil
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

inline fun <T> retrofitCallback(
    crossinline fn: (Response<T>?, Throwable?) -> Unit
): Callback<T> {
    return object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (!response.isSuccessful && NetworkUtil.isInvalidTokenErr(response.errorBody())) {
                val refreshResponse = runBlocking {
                    SignRepository.refreshToken()
                }
                if (refreshResponse == null) {
                    fn(response, null)
                    return
                }
                if (refreshResponse.isSuccessful && refreshResponse.body() != null) {
                    call.clone().enqueue(this)
                    return
                }
            }
            fn(response, null)
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            fn(null, t)
        }
    }
}