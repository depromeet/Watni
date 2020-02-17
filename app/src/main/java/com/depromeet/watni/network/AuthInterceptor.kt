package com.depromeet.watni.network

import com.depromeet.watni.clientId
import com.depromeet.watni.clientSecret
import com.depromeet.watni.ext.base64Encoding
import com.depromeet.watni.util.SharedPrefUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor : Interceptor {
    private val headerName = "Authorization"
    private val authHeader = "$clientId:$clientSecret".base64Encoding()

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val token = SharedPrefUtil.getAccessToken()
        val headerValue = if (token.isNullOrEmpty()) {
            authHeader.base64Encoding()
        } else {
            "Bearer $token"
        }

        proceed(request().newBuilder().addHeader(headerName, headerValue).build())
    }
}