package com.depromeet.watni

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matchers
import org.junit.Test

/*
 * Created by yunji on 18/04/2020
 */
class ApplicationTest {

    @Test
    fun useInstrumentationContext() {
        val instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        val packageName = instrumentationContext.packageName
        assertThat("com.depromeet.watni.debug.test", Matchers.`is`(packageName))
    }

    @Test
    fun useAppContextNewRecommendedWay() {
        val app: Context = ApplicationProvider.getApplicationContext()
        assertThat("com.depromeet.watni.debug", Matchers.`is`(app.packageName))
    }
}