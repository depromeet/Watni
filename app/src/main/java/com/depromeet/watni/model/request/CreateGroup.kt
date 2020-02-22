package com.depromeet.watni.model.request


import com.google.gson.annotations.SerializedName

data class CreateGroup(
    @SerializedName("description")
    val description: String,
    @SerializedName("groupName")
    val groupName: String
)