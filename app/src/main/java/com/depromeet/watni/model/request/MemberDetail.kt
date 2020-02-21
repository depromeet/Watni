package com.depromeet.watni.model.request


import com.google.gson.annotations.SerializedName

data class MemberDetail(
    @SerializedName("group")
    val group: Group,
    @SerializedName("manager")
    val manager: Boolean
)