package com.depromeet.watni.util

import java.text.SimpleDateFormat
import java.util.*

/*
 * Created by yunji on 14/04/2020
 */
object DateTimeUtil {
    private const val UNIX_TRANS_NUM = 1000
    private val dateFormat = SimpleDateFormat("yyyy.M.d (EEE)", Locale.KOREA)
    private val dateKorFormat = SimpleDateFormat("yyyy년 M월 d일 (EEE)", Locale.KOREA)
    private val timeFormat = SimpleDateFormat("K:mm", Locale.KOREA)
    private val ampmFormat = SimpleDateFormat("a ", Locale.KOREA)

    fun getReadableDateString(timestamp: Long): String = dateFormat.format(Date(timestamp * UNIX_TRANS_NUM))

    fun getReadableTimeString(timestamp: Long): String = timeFormat.format(Date(timestamp * UNIX_TRANS_NUM))

    fun getReadableAmPmString(timestamp: Long): String = ampmFormat.format(Date(timestamp * UNIX_TRANS_NUM))

    fun getReadableTimeRange(startAt: Long, endAt: Long): String {
        val startTimeString = getReadableAmPmString(startAt) + getReadableTimeString(startAt)
        val endTimeRange = getReadableAmPmString(endAt)
        val endTimeString = if (startTimeString.startsWith(endTimeRange)) {
            getReadableTimeString(endAt)
        } else {
            endTimeRange + getReadableTimeString(endAt)
        }

        return "$startTimeString - $endTimeString"
    }

    fun getTodayString(): String = dateKorFormat.format(Calendar.getInstance().time).toString()

    fun getTodayString(date: Date): String = dateKorFormat.format(date).toString()

    fun getTodayString(calendar: Calendar): String = dateKorFormat.format(calendar.time).toString()
}