package com.depromeet.watni.network

import com.depromeet.watni.model.request.RefreshToken
import com.depromeet.watni.model.request.UserJoin
import com.depromeet.watni.model.request.UserLogin
import com.depromeet.watni.model.response.IssueTokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignApi {

    @POST("/oauth/token")
    fun issueToken(@Body user: UserLogin): Call<IssueTokenResponse>

    @POST("/oauth/token")
    fun refreshToken(@Body token: RefreshToken): Call<IssueTokenResponse>

    @POST("/sign-up")
    fun userJoin(@Body user: UserJoin): Call<Void>
}