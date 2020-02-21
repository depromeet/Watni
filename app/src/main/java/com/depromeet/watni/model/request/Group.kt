package com.depromeet.watni.model.request


import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("conferences")
    val conferences: List<Conference>,
    @SerializedName("groupId")
    val groupId: Int,
    @SerializedName("name")
    val name: String
)