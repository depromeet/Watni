package com.depromeet.watni.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val originRequest = request().newBuilder()
        val requestWithAuthHeader = originRequest
            .addHeader(HEADER_AUTH, HeaderProvider.getAuthHeaderValue())
            .build()

        proceed(requestWithAuthHeader)
    }
}