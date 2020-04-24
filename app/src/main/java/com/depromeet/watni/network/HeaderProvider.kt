package com.depromeet.watni.network

import com.depromeet.watni.ext.base64Encoding
import com.depromeet.watni.util.SharedPrefUtil

object HeaderProvider {
    private val basicAuthHeader = "$CLIENT_ID:$CLIENT_SECRET".base64Encoding()

    fun getIssueTokenHeader(): Map<String, String> = mutableMapOf<String, String>().apply {
        put(HEADER_AUTH, "$BASIC_TOKEN $basicAuthHeader")
        put(CONTENT_TYPE, TYPE_JSON)
        put(ACCEPT_TYPE, TYPE_JSON)
    }

    fun getAuthHeaderValue(): String {
        val token = SharedPrefUtil.getAccessToken()
        return if (token.isNullOrEmpty()) basicAuthHeader else "Bearer $token"
    }
}