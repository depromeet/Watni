package com.depromeet.watni.model.request


import com.depromeet.watni.network.APPLY_TYPE
import com.google.gson.annotations.SerializedName

open class GroupCode(
    @SerializedName("applyType")
    open val applyType: String = APPLY_TYPE
)