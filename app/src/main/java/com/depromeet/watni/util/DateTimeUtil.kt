package com.depromeet.watni.util

import java.text.SimpleDateFormat
import java.util.*

/*
 * Created by yunji on 14/04/2020
 */
object DateTimeUtil {
    private const val UNIX_TRANS_NUM = 1000
    private val dateFormat = SimpleDateFormat("yyyy.M.d (EEE)", Locale.KOREA)
    private val timeFormat = SimpleDateFormat("K:mm", Locale.KOREA)
    private val ampmFormat = SimpleDateFormat("a", Locale.KOREA)

    fun getReadableDateString(timestamp: Long): String = dateFormat.format(Date(timestamp * UNIX_TRANS_NUM))

    fun getReadableTimeString(timestamp: Long): String = timeFormat.format(Date(timestamp * UNIX_TRANS_NUM))

    fun getReadableTimeRange(startAt: Long, endAt: Long): String {
        val date = Date(startAt * UNIX_TRANS_NUM)
        return "${ampmFormat.format(date)} ${getReadableTimeString(startAt)} - ${getReadableTimeString(endAt)}"
    }
}