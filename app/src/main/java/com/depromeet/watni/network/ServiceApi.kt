package com.depromeet.watni.network

import com.depromeet.watni.model.request.RefreshToken
import com.depromeet.watni.model.request.User
import com.depromeet.watni.model.response.IssueTokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceApi {

    @POST("/oauth/token")
    fun issueToken(@Body user: User): Call<IssueTokenResponse>

    @POST("/oauth/token")
    fun refreshToken(@Body token: RefreshToken): Call<IssueTokenResponse>

    @POST("/sign-up")
    fun userJoin(@Body user: User): Call<Void>
}