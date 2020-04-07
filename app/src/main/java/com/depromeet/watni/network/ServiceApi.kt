package com.depromeet.watni.network

import com.depromeet.watni.model.request.*
import com.depromeet.watni.model.response.GroupCodeResponse
import com.depromeet.watni.model.response.IssueTokenResponse
import com.depromeet.watni.model.response.JoinGroupResponse
import com.depromeet.watni.model.response.SearchGroupResponse
import retrofit2.Call
import retrofit2.http.*

interface ServiceApi {

    // 인증
    @POST("/oauth/token")
    fun issueToken(@Body user: UserLogin, @HeaderMap headers: Map<String, String>): Call<IssueTokenResponse>

    @POST("/oauth/token")
    fun refreshToken(@Body token: RefreshToken, @HeaderMap headers: Map<String, String>): Call<IssueTokenResponse>

    // 회원가입
    @POST("/sign-up")
    fun userJoin(@Body user: UserJoin): Call<Void>

    // 내 정보
    @GET("/api/user/me")
    fun getUserInfo(): Call<User>

    // 그룹 생성, 검색
    @GET("/api/group/{groupId}")
    fun searchGroup(@Path("groupId") groupId: Int): Call<SearchGroupResponse>

    @POST("/api/group")
    fun createGroup(@Body createGroup: CreateGroup): Call<SearchGroupResponse>

    @POST("/api/group/{groupId}/apply-way")
    fun createGroupCode(@Path("groupId") groupId: Int, @Body groupCode: NewGroupCode): Call<GroupCodeResponse>

    @GET("/api/group/{groupId}/apply-way")
    fun getGroupCode(@Path("groupId") groupId: Int, @Body groupCode: GroupCode): Call<GroupCodeResponse>

    @GET("/api/group/{groupId}/apply-way/check")
    fun checkGroupCode(@Path("groupId") groupId: Int, @Body groupCode: NewGroupCode): Call<Void>

    // 그룹 가입
    @POST("/api/group/accession")
    fun joinGroup(@Body joinGroup: JoinGroup): Call<JoinGroupResponse>
}