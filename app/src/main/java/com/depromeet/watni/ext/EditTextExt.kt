package com.depromeet.watni.ext

import com.google.android.material.textfield.TextInputLayout


fun TextInputLayout.showMessage(strResId: Int) {
    this.apply {
        error = context.getString(strResId)
        requestFocus()
    }
}

fun TextInputLayout.hideMessage() {
    this.error = null
}