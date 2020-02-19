package com.depromeet.watni.model.request


import com.google.gson.annotations.SerializedName

data class Conference(
    @SerializedName("conferenceId")
    val conferenceId: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("locationInfo")
    val locationInfo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("notice")
    val notice: String?,
    @SerializedName("photoUrl")
    val photoUrl: String?,
    @SerializedName("startAt")
    val startAt: Long?,
    @SerializedName("endAt")
    val endAt: Long?
)