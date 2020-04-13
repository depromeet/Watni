package com.depromeet.watni.util

import android.widget.Toast
import androidx.annotation.StringRes
import com.depromeet.watni.MainApplication

fun showToast(text: String?) {
    Toast.makeText(MainApplication.getContext(), text, Toast.LENGTH_SHORT).show()
}

fun showToast(@StringRes resId: Int) {
    val context = MainApplication.getContext()
    Toast.makeText(context, context.getString(resId), Toast.LENGTH_SHORT).show()
}