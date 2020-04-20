package com.depromeet.watni.util

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidatorTest {

    @Test
    fun testEmailValidation() {
        assertTrue(InputValidator.isValidEmail("abc@abc.com"))
        assertFalse(InputValidator.isValidEmail("a.a"))
        assertFalse(InputValidator.isValidEmail(""))
    }

    @Test
    fun testNameValidation() {
        assertTrue(InputValidator.isValidName("yunji"))
        assertFalse(InputValidator.isValidName("aldlkwandwa"))
        assertFalse(InputValidator.isValidName(" "))
    }

    @Test
    fun testPwdValidation() {
        assertTrue(InputValidator.isValidPwd("adkAx123"))
        assertFalse(InputValidator.isValidPwd("kadkaw219231ldj2109"))
        assertFalse(InputValidator.isValidPwd("jdiwa"))
    }

    @Test
    fun testIsSamePwd() {
        assertTrue(InputValidator.isSamePwd("abcd123", "abcd123"))
    }

    @Test
    fun testIsValidJoinInfo() {
        assertTrue(
            InputValidator.isValidJoinInfo("yunji", "yunji@naver.com", "abcd123", "abcd123")
        )
    }
}
