package com.depromeet.watni.model.request


import com.depromeet.watni.model.response.GroupResponse
import com.google.gson.annotations.SerializedName

data class MemberDetail(
    @SerializedName("group")
    val group: GroupResponse,
    @SerializedName("manager")
    val manager: Boolean
)