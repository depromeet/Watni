package com.depromeet.watni.ui

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.IntRange
import androidx.appcompat.widget.AppCompatEditText
import com.depromeet.watni.util.DateTimeUtil


/*
 * Created by yunji on 20/04/2020
 */
class TimeRangeEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {

    init {
        isClickable = true
        setText(DateTimeUtil.getCurrentTimeRangeString())
    }

    fun setTimeText(
        @IntRange(from = DateTimeUtil.HOUR_MIN, to = DateTimeUtil.HOUR_MAX) startHour: Int,
        @IntRange(from = DateTimeUtil.MINUTES_MIN, to = DateTimeUtil.MINUTES_MAX - 1) startMin: Int,
        @IntRange(from = DateTimeUtil.HOUR_MIN, to = DateTimeUtil.HOUR_MAX) endHour: Int,
        @IntRange(from = DateTimeUtil.MINUTES_MIN, to = DateTimeUtil.MINUTES_MAX - 1) endMin: Int
    ) {
        setText(DateTimeUtil.getReadableTimeString(startHour, startMin, endHour, endMin))
    }
}