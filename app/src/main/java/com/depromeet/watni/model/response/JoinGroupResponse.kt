package com.depromeet.watni.model.response


import com.depromeet.watni.model.request.Conference
import com.google.gson.annotations.SerializedName

data class JoinGroupResponse(
    @SerializedName("conferences")
    val conferences: List<Conference>,
    @SerializedName("groupId")
    val groupId: Int,
    @SerializedName("name")
    val name: String
)