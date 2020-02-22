package com.depromeet.watni.network

import com.depromeet.watni.model.request.*
import com.depromeet.watni.model.response.IssueTokenResponse
import com.depromeet.watni.model.response.SearchGroupResponse
import retrofit2.Call
import retrofit2.http.*

interface ServiceApi {

    @POST("/oauth/token")
    fun issueToken(@Body user: UserLogin, @HeaderMap headers: Map<String, String>): Call<IssueTokenResponse>

    @POST("/oauth/token")
    fun refreshToken(@Body token: RefreshToken, @HeaderMap headers: Map<String, String>): Call<IssueTokenResponse>

    @POST("/sign-up")
    fun userJoin(@Body user: UserJoin): Call<Void>

    @GET("/api/user/me")
    fun getUserInfo(): Call<User>

    @GET("/api/group/{groupId}")
    fun searchGroup(@Path("groupId") groupId: Int): Call<SearchGroupResponse>

    @POST("/api/group")
    fun createGroup(@Body createGroup: CreateGroup): Call<SearchGroupResponse>
}