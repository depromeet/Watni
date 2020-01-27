package com.depromeet.watni.util

import android.util.TypedValue
import com.depromeet.watni.MainApplication

fun dpToPx(dp: Float) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, dp, MainApplication.getContext().resources.displayMetrics
).toInt()