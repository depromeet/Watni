package com.depromeet.watni.model.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error")
    val error: String,
    @SerializedName("error_description")
    val description: String
)