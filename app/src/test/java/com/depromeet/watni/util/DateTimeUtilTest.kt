package com.depromeet.watni.util

import org.junit.Assert.assertEquals
import org.junit.Test

/*
 * Created by yunji on 18/04/2020
 */
class DateTimeUtilTest {

    @Test
    fun getReadableDateString() {
        assertEquals(DateTimeUtil.getReadableDateString(1587213622), "2020.4.18 (토)")
    }

    @Test
    fun getReadableTimeString() {
        assertEquals(DateTimeUtil.getReadableTimeString(158721362), "오전 10:16")
    }

    @Test
    fun getReadableTimeRange() {
        assertEquals(DateTimeUtil.getReadableTimeRange(1587213622, 1587213693), "오후 9:40 - 오후 9:41")
    }
}