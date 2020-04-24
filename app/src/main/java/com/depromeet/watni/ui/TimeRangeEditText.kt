package com.depromeet.watni.ui

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.IntRange
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.math.MathUtils
import com.depromeet.watni.util.DateTimeUtil


/*
 * Created by yunji on 20/04/2020
 */
class TimeRangeEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {
    var startHour = 0
        set(value) {
            field = MathUtils.clamp(value, 1, 12)
            setText(DateTimeUtil.getReadableTimeString(startHour, startMin, endHour, endMin))
        }
    var startMin = 0
        set(value) {
            field = MathUtils.clamp(value, 1, 60)
            setText(DateTimeUtil.getReadableTimeString(startHour, startMin, endHour, endMin))
        }
    var endHour = 0
        set(value) {
            field = MathUtils.clamp(value, 1, 12)
            setText(DateTimeUtil.getReadableTimeString(startHour, startMin, endHour, endMin))
        }
    var endMin = 0
        set(value) {
            field = MathUtils.clamp(value, 1, 60)
            setText(DateTimeUtil.getReadableTimeString(startHour, startMin, endHour, endMin))
        }

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
        this.startHour = startHour
        this.startMin = startMin
        this.endHour = endHour
        this.endMin = endMin
    }

    fun getStartTimestamp() = 1000 * ((startHour * 60) + startMin)

    fun getEndTimestamp() = 1000 * ((endHour * 60) + endHour)
}