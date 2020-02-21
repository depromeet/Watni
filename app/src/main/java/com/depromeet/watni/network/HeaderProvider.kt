package com.depromeet.watni.network

import com.depromeet.watni.ext.base64Encoding

object HeaderProvider {
    private val authHeader = "$CLIENT_ID:$CLIENT_SECRET".base64Encoding()

    fun createIssueTokenHeader(): Map<String, String> = mutableMapOf<String, String>().apply {
        put(HEADER_AUTH, "$BASIC_TOKEN $authHeader")
        put(CONTENT_TYPE, TYPE_JSON)
        put(ACCEPT_TYPE, TYPE_JSON)
    }
}