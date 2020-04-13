package com.depromeet.watni.util

import android.util.TypedValue
import androidx.annotation.Dimension
import com.depromeet.watni.MainApplication

fun dpToPx(@Dimension(unit = Dimension.DP) dp: Float) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, dp, MainApplication.getContext().resources.displayMetrics
).toInt()