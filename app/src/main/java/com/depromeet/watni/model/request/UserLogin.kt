package com.depromeet.watni.model.request


import com.google.gson.annotations.SerializedName

data class UserLogin(
    @SerializedName("grant_type")
    val grantType: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)