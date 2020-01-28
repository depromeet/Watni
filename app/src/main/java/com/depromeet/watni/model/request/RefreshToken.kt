package com.depromeet.watni.model.request


import com.google.gson.annotations.SerializedName

data class RefreshToken(
    @SerializedName("grant_type")
    val grantType: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)