package com.depromeet.watni.model.request


import com.google.gson.annotations.SerializedName

data class JoinGroup(
    @SerializedName("code")
    val code: String
)