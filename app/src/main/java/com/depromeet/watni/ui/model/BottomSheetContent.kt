package com.depromeet.watni.ui.model

import com.depromeet.watni.ui.PickBottomSheetFragment

/*
 * Created by yunji on 22/04/2020
 */
data class BottomSheetContent(
    val text: String? = "",
    val onClick: (pickBottomSheetFragment: PickBottomSheetFragment) -> Unit
)