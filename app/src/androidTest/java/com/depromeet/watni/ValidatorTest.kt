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
}
