package com.depromeet.watni.model.response


import com.depromeet.watni.model.request.Conference
import com.google.gson.annotations.SerializedName

data class GroupResponse(
    @SerializedName("conferences")
    val conferences: List<Conference> = listOf(),
    @SerializedName("attendances")
    val attendances: List<Any> = listOf(),
    @SerializedName("groupId")
    val groupId: Int = -1,
    @SerializedName("name")
    val name: String = ""
)