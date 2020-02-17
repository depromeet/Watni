package com.depromeet.watni.util

import com.depromeet.watni.MainApplication

object ResourceUtil {

    fun getColor(resId: Int) = MainApplication.getContext().getColor(resId)

    fun getDrawable(resId: Int) = MainApplication.getContext().getDrawable(resId)

    fun getString(resId: Int) = MainApplication.getContext().getString(resId)
}