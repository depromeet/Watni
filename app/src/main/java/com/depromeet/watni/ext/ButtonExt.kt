package com.depromeet.watni.ext

import android.graphics.Color
import android.view.View
import android.widget.Button
import com.depromeet.watni.R
import com.depromeet.watni.ui.model.BasicButtonContent
import com.depromeet.watni.util.ResourceUtil

/*
 * Created by yunji on 21/04/2020
 */
fun Button.setUsable(
    usable: Boolean,
    usableColorResId: Int = Color.BLACK,
    disableColorResId: Int = ResourceUtil.getColor(R.color.color_4D000000)
) {
    isEnabled = usable
    setTextColor(if (usable) usableColorResId else disableColorResId)
}

fun Button.bindBasicContent(basicButtonContent: BasicButtonContent?) {
    if (basicButtonContent == null) {
        visibility = View.GONE
        return
    }

    text = basicButtonContent.text
    setOnClickListener { run(basicButtonContent.onClick) }
}