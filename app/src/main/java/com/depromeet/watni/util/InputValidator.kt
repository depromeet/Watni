package com.depromeet.watni.util


object InputValidator {
    private const val NAME_LENGTH_MIN = 2
    private const val NAME_LENGTH_MAX = 10
    private const val PWD_LENGTH_MIN = 6
    private const val PWD_LENGTH_MAX = 12
    private const val PWD_PATTERN = "^[A-Za-z0-9]{$PWD_LENGTH_MIN,$PWD_LENGTH_MAX}$"

    fun isNameValid(name: String) = name.length in NAME_LENGTH_MIN..NAME_LENGTH_MAX
            && name.isNotBlank()

    fun isEmailValid(email: String) = email.contains("@")

    fun isPwdValid(pwd: String) = PWD_PATTERN.toRegex().matches(pwd)
}