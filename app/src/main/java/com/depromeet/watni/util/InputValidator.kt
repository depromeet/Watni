package com.depromeet.watni.util


object InputValidator {
    private const val NAME_LENGTH_MAX = 10
    private const val PWD_LENGTH_MIN = 8
    private const val PWD_LENGTH_MAX = 16
    private const val PHONE_PATTERN = "^[0-9]{3}[-]+[0-9]{4}[-]+[0-9]{4}$"
    private const val PWD_PATTERN = "^[A-Za-z0-9]{$PWD_LENGTH_MIN,$PWD_LENGTH_MAX}$"

    fun isNameValid(name: String) = (name.length <= NAME_LENGTH_MAX)
            && name.isNotBlank()

    fun isEmailValid(email: String) = email.contains("@")

    fun isPwdValid(pwd: String) = PWD_PATTERN.toRegex().matches(pwd)

    fun isPhoneNumberValid(phoneNumber: String) = PHONE_PATTERN.toRegex().matches(phoneNumber)
}