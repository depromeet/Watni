package com.depromeet.watni.model.response


import com.google.gson.annotations.SerializedName

data class GroupCodeResponse(
    @SerializedName("code")
    val code: String
)