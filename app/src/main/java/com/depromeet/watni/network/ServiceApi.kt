package com.depromeet.watni.network

import com.depromeet.watni.model.request.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceApi {

    @POST("sign-up")
    fun userJoin(@Body user: User): Call<Void>
}