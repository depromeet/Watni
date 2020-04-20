package com.depromeet.watni.ext

import android.graphics.Color
import android.widget.Button
import com.depromeet.watni.R
import com.depromeet.watni.util.ResourceUtil

/*
 * Created by yunji on 21/04/2020
 */
fun Button.setUsable(usable: Boolean) {
    isEnabled = usable
    setTextColor(if (usable) Color.BLACK else ResourceUtil.getColor(R.color.color_4D000000))
}