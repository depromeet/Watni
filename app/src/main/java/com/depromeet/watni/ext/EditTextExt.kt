package com.depromeet.watni.ext

import com.google.android.material.textfield.TextInputLayout


fun TextInputLayout.showMessage(strResId: Int) {
    this.error = context.getString(strResId)
}

fun TextInputLayout.hideMessage() {
    this.error = null
}