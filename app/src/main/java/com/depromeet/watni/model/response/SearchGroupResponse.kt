package com.depromeet.watni.model.response


import android.telecom.Conference
import com.google.gson.annotations.SerializedName

data class SearchGroupResponse(
    @SerializedName("conferences")
    val conferences: List<Conference>,
    @SerializedName("groupId")
    val groupId: Int,
    @SerializedName("name")
    val name: String
)