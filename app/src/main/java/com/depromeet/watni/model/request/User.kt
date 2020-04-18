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

    fun hasConference() = memberDetails.isNotEmpty() && memberDetails[0].group.conferences.isNotEmpty()
}