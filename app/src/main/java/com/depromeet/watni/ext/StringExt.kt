package com.depromeet.watni.ext

import android.util.Base64


fun String.base64Encoding(): String = Base64.encodeToString(
    toByteArray(), Base64.NO_WRAP
)