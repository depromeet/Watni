package com.depromeet.watni.network

import com.depromeet.watni.ext.base64Encoding
import com.depromeet.watni.util.SharedPrefUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor : Interceptor {
    private val authHeader = "$CLIENT_ID:$CLIENT_SECRET".base64Encoding()

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val token = SharedPrefUtil.getAccessToken()
        val headerValue = if (token.isNullOrEmpty()) {
            "Basic $authHeader"
        } else {
            "Bearer $token"
        }

        proceed(this.request().newBuilder().addHeader(HEADER_AUTH, headerValue).build())
    }
}