package com.depromeet.watni.util

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.depromeet.watni.MainApplication

object ResourceUtil {

    fun getColor(@ColorRes resId: Int) = MainApplication.getContext().getColor(resId)

    fun getDrawable(@DrawableRes resId: Int) = MainApplication.getContext().getDrawable(resId)

    fun getString(@StringRes resId: Int) = MainApplication.getContext().getString(resId)

    fun getStringArray(@StringRes resId: Int): Array<String> = MainApplication.getContext().resources.getStringArray(resId)
}