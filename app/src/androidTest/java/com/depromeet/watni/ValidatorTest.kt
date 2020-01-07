package com.depromeet.watni

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.depromeet.watni.util.InputValidator
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class ValidatorTest {

    @Test
    fun testEmailValidation() {
        assertTrue(InputValidator.isEmailValid("abc@abc.com"))
        assertFalse(InputValidator.isEmailValid("a.a"))
        assertFalse(InputValidator.isEmailValid(""))
    }

    @Test
    fun testNameValidation() {
        assertTrue(InputValidator.isNameValid("yunji"))
        assertFalse(InputValidator.isNameValid("aldlkwandwa"))
        assertFalse(InputValidator.isNameValid(" "))
    }

    @Test
    fun testPwdValidation() {
        assertTrue(InputValidator.isPwdValid("adkAx123"))
        assertFalse(InputValidator.isPwdValid("kadkaw219231ldj2109"))
        assertFalse(InputValidator.isPwdValid("jdiwa"))
    }

    @Test
    fun testPhoneNumberValidation() {
        assertTrue(InputValidator.isPhoneNumberValid("010-1234-5678"))
        assertFalse(InputValidator.isPhoneNumberValid("01012345678"))
        assertFalse(InputValidator.isPhoneNumberValid("010-123-567"))
        assertFalse(InputValidator.isPhoneNumberValid("010-12355678"))
        assertFalse(InputValidator.isPhoneNumberValid(""))
    }
}
