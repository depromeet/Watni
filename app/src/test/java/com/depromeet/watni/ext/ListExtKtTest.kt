package com.depromeet.watni.ext

import org.junit.Assert.assertTrue
import org.junit.Test

/*
 * Created by yunji on 18/04/2020
 */
class ListExtKtTest {

    @Test
    fun testRandom() {
        val list = listOf(1, 2, 3, 4, 5)
        val array = arrayOf(1, 2, 3, 4, 5)

        assertTrue(list.contains(list.random()))
        assertTrue(array.contains(array.random()))
    }
}