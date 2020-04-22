package com.depromeet.watni.util

import org.junit.Assert.*
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
        assertEquals(DateTimeUtil.getReadableTimeString(158721362), "10:16")
    }

    @Test
    fun getReadableTimeRange() {
        assertEquals(DateTimeUtil.getReadableTimeRange(1587213622, 1587213693), "오후 9:40 - 9:41")
    }

    @Test
    fun getAmPm() {
        assertEquals(DateTimeUtil.getAmPm(13), "오후")
    }

    @Test
    fun getReadableAmPmString() {
        assertEquals(DateTimeUtil.getReadableAmPmString(1587213622).trim(), "오후")
    }

    @Test
    fun isCorrectSequence() {
        assertTrue(DateTimeUtil.isCorrectSequence(14, 1, 14, 2))
        assertFalse(DateTimeUtil.isCorrectSequence(14, 1, 13, 2))
    }
}