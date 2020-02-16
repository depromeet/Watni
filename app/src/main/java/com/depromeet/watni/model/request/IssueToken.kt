package com.depromeet.watni.model.request


import com.google.gson.annotations.SerializedName

data class IssueToken(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("grant_type")
    val grantType: String = "password"
)