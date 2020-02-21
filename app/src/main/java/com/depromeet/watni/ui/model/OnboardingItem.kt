package com.depromeet.watni.ui.model

/*
 * Created by yunji on 2020-02-21
 */
data class OnboardingItem(
    val asset: String,
    val msg: String,
    val subMsg: String,
    val msgVisibility: Boolean = !msg.isBlank()
)