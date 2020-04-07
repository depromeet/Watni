package com.depromeet.watni.model.request


import com.google.gson.annotations.SerializedName

data class NewGroupCode(
    @SerializedName("content")
    val content: String
) : GroupCode()