package com.depromeet.watni.ext

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.depromeet.watni.R
import com.depromeet.watni.util.ResourceUtil
import com.google.android.material.snackbar.Snackbar

/*
 * Created by yunji on 14/04/2020
 */
fun View.showSnack(
    @StringRes resId: Int,
    length: Int = Snackbar.LENGTH_LONG,
    @ColorRes color: Int? = null
) {
    showSnack(ResourceUtil.getString(resId), length, color)
}

fun View.showSnack(
    message: String,
    length: Int = Snackbar.LENGTH_LONG,
    @ColorRes color: Int? = null
) {
    Snackbar.make(this, message, length).run {
        color?.let { setActionTextColor(ContextCompat.getColor(context, color)) }
        setAction(ResourceUtil.getString(R.string.ok)) { dismiss() }
        show()
    }
}