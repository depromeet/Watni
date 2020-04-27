package com.depromeet.watni.model.request


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    val email: String,
    @SerializedName("memberDetails")
    val memberDetails: List<MemberDetail>,
    @SerializedName("name")
    val name: String
) {

    fun hasGroup() = memberDetails.isNotEmpty()

    fun getGroupId() = if (hasGroup()) memberDetails[0].group.groupId else -1

    fun hasConference() = hasGroup() && memberDetails[0].group.conferences.isNotEmpty()

    fun isManager() = memberDetails.isNotEmpty() && memberDetails[0].manager
}