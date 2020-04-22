package com.depromeet.watni.ui

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/*
 * Created by yunji on 22/04/2020
 */
class RangeTimePickerTest {
    private lateinit var rangeTimePicker: RangeTimePicker

    @Before
    fun setUp() {
        val app: Context = ApplicationProvider.getApplicationContext()
        rangeTimePicker = RangeTimePicker(app)
    }

    @Test
    fun testSetTimeInterval() {
        rangeTimePicker.timeInterval = 60
        assertEquals(rangeTimePicker.timeInterval, rangeTimePicker.maxInterval)
    }

    @Test
    fun testGetDisplayedMinutes() {
        val method = rangeTimePicker::class.java.getDeclaredMethod("getDisplayedValue", Int::class.java)
        method.isAccessible = true
        val result: Array<String> = method.invoke(rangeTimePicker, 10) as Array<String>
        assertArrayEquals(result, arrayOf("00", "10", "20", "30", "40", "50"))
    }
}