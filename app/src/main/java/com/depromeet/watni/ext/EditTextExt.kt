package com.depromeet.watni.ext

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.updateStatus(isValid: Boolean, strResId: Int) {
    if (isValid) {
        hideMessage()
    } else {
        showMessage(strResId)
    }
}

fun TextInputLayout.showMessage(strResId: Int) {
    apply {
        error = context.getString(strResId)
        requestFocus()
    }
}

fun TextInputLayout.hideMessage() {
    error = null
}