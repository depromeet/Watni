package com.depromeet.watni.util

import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.depromeet.watni.MainApplication
import com.depromeet.watni.R

object ResourceUtil {

    fun getColor(@ColorRes resId: Int) = MainApplication.getContext().getColor(resId)

    fun getDrawable(@DrawableRes resId: Int) = MainApplication.getContext().getDrawable(resId)

    fun getString(@StringRes resId: Int) = MainApplication.getContext().getString(resId)

    fun getStringArray(@ArrayRes resId: Int): Array<String> = MainApplication.getContext().resources.getStringArray(resId)

    fun getRandomWelcomeString(name: String): String {
        val msgArray = getStringArray(R.array.msg_welcome_random)
        return String.format(msgArray.random(), name)
    }
}