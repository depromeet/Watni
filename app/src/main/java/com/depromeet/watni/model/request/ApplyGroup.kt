package com.depromeet.watni.model.request


import com.google.gson.annotations.SerializedName

data class ApplyGroup(
    @SerializedName("applyType")
    val applyType: String = "CODE",
    @SerializedName("content")
    val content: String
)