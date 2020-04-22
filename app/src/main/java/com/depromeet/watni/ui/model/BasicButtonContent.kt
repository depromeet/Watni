package com.depromeet.watni.ui.model

import android.view.View

/*
 * Created by yunji on 22/04/2020
 */
data class BasicButtonContent(
    val text: String? = "",
    val onClick: (view: View) -> Unit
)