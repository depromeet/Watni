package com.depromeet.watni.util

import com.depromeet.watni.ext.isNotNullOrBlank


object EditInputValidator {
    private const val LENGTH_MIN = 1
    private const val TITLE_LENGTH_MAX = 15

    fun isValidTitle(text: String?) =
        text.isNotNullOrBlank() && text!!.length in LENGTH_MIN..TITLE_LENGTH_MAX

    fun isValidConference(titleText: String?, placeText: String?) =
        isValidTitle(titleText) && placeText.isNotNullOrBlank()
}