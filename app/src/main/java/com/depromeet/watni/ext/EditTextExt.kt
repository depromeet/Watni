package com.depromeet.watni.ext

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.updateStatus(messageVisibility: Boolean, strResId: Int) {
    if (messageVisibility) {
        this.showMessage(strResId)
    } else {
        this.hideMessage()
    }
}

fun TextInputLayout.showMessage(strResId: Int) {
    this.apply {
        error = context.getString(strResId)
        requestFocus()
    }
}

fun TextInputLayout.hideMessage() {
    this.error = null
}