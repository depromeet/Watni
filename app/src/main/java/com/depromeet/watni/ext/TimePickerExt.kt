package com.depromeet.watni.ext

import android.annotation.SuppressLint
import android.widget.NumberPicker
import android.widget.TimePicker
import androidx.annotation.IntRange
import com.depromeet.watni.util.DateTimeUtil
import com.depromeet.watni.util.DateTimeUtil.MINUTES_MAX
import com.depromeet.watni.util.DateTimeUtil.MINUTES_MIN

/*
 * Created by yunji on 21/04/2020
 */
const val DEFAULT_INTERVAL = 5
const val MIN_INTERVAL = 1L
const val MAX_INTERVAL = 30L

@SuppressLint("PrivateApi")
fun TimePicker.setTimeInterval(
    @IntRange(from = MIN_INTERVAL, to = MAX_INTERVAL)
    timeInterval: Int = DEFAULT_INTERVAL
) {
    try {
        val classForId = Class.forName("com.android.internal.R\$id")
        val fieldId = classForId.getField("minute").getInt(null)
        (this.findViewById(fieldId) as NumberPicker).apply {
            minValue = MINUTES_MIN.toInt()
            maxValue = MINUTES_MAX.toInt() / timeInterval - 1
            displayedValues = getDisplayedValue(timeInterval)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun TimePicker.getDisplayedMinutes(
    @IntRange(from = MIN_INTERVAL, to = MAX_INTERVAL)
    timeInterval: Int = DEFAULT_INTERVAL
): Int = minute * timeInterval

private fun getDisplayedValue(
    @IntRange(from = MIN_INTERVAL, to = MAX_INTERVAL)
    timeInterval: Int = DEFAULT_INTERVAL
): Array<String> {
    val result = ArrayList<String>()
    for (i in 0 until MINUTES_MAX.toInt() step timeInterval) {
        result.add(String.format(DateTimeUtil.MINUTE_FORMAT, i))
    }
    return result.toArray(arrayOf(""))
}