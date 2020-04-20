package com.depromeet.watni.ext;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/*
 * Created by yunji on 21/04/2020
 */
public class TimePickerExtKtTest {

    @Test
    public void testGetDeclaredMethod() throws Exception {
        Method displayedValue = TimePickerExtKt.class.getDeclaredMethod("getDisplayedValue", int.class);
        displayedValue.setAccessible(true);

        String[] result = (String[]) displayedValue.invoke(displayedValue, 10);
        Assert.assertArrayEquals(result, new String[]{"00", "10", "20", "30", "40", "50"});
    }
}