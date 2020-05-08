package com.depromeet.watni.util

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidatorTest {

    @Test
    fun testEmailValidation() {
        assertTrue(JoinInputValidator.isValidEmail("abc@abc.com"))
        assertFalse(JoinInputValidator.isValidEmail("a.a"))
        assertFalse(JoinInputValidator.isValidEmail(""))
    }

    @Test
    fun testNameValidation() {
        assertTrue(JoinInputValidator.isValidName("yunji"))
        assertFalse(JoinInputValidator.isValidName("aldlkwandwa"))
        assertFalse(JoinInputValidator.isValidName(" "))
    }

    @Test
    fun testPwdValidation() {
        assertFalse(JoinInputValidator.isValidPwd("kadkaw219231ldj2109"))
        assertFalse(JoinInputValidator.isValidPwd("jdiwa"))
    }

    @Test
    fun testIsSamePwd() {
        assertTrue(JoinInputValidator.isSamePwd("abcd123", "abcd123"))
    }

    @Test
    fun testIsValidJoinInfo() {
        assertTrue(
            JoinInputValidator.isValidJoinInfo("yunji", "yunji@naver.com", "abcd123", "abcd123")
        )
    }
}
