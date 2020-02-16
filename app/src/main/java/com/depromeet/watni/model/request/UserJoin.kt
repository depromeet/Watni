package com.depromeet.watni.model.request

data class UserJoin(
    val email: String,
    val name: String,
    val password: String,
    val passwordConfirm: String
)