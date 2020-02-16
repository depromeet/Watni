package com.depromeet.watni.util


object InputValidator {
    private const val NAME_LENGTH_MIN = 2
    private const val NAME_LENGTH_MAX = 10
    private const val PWD_LENGTH_MIN = 6
    private const val PWD_LENGTH_MAX = 12
    private const val PWD_PATTERN = "^[A-Za-z0-9]{$PWD_LENGTH_MIN,$PWD_LENGTH_MAX}$"

    fun isValidName(name: String?) =
        !name.isNullOrBlank() && name.length in NAME_LENGTH_MIN..NAME_LENGTH_MAX

    fun isValidEmail(email: String?) = !email.isNullOrBlank() && email.contains("@")

    fun isValidPwd(pwd: String?) = !pwd.isNullOrBlank()
            && PWD_PATTERN.toRegex().matches(pwd)

    fun isSamePwd(pwd: String?, pwdConfirm: String?) = !pwd.isNullOrBlank()
            && !pwdConfirm.isNullOrBlank()
            && (pwd == pwdConfirm)

    fun isJoinInfoValid(name: String?, email: String?, pwd: String?, pwdConfirm: String?) =
        isValidName(name) && isValidEmail(email) && isSamePwd(pwd, pwdConfirm)
}