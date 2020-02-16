package com.depromeet.watni.model.request


import com.google.gson.annotations.SerializedName

data class UserLogin(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String
)