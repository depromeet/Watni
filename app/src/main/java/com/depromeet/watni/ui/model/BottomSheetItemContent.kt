package com.depromeet.watni.ui.model

import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/*
 * Created by yunji on 22/04/2020
 */
data class BottomSheetItemContent(
    val text: String? = "",
    val onClick: (dialogFragment: BottomSheetDialogFragment) -> Unit
)