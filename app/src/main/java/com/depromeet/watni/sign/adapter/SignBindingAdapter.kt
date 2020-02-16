package com.depromeet.watni.sign.adapter

import android.widget.Button
import androidx.databinding.BindingAdapter
import com.depromeet.watni.R
import com.depromeet.watni.util.ResourceUtil

@BindingAdapter("setEnable")
fun setJoinBtnEnable(button: Button, isAvailable: Boolean) {
    button.apply {
        setTextColor(ResourceUtil.getColor(if (isAvailable) android.R.color.white else R.color.color_gray))
        setBackgroundResource(if (isAvailable) R.drawable.shape_rect_stroke_red else R.drawable.shape_rect_stroke_gray)
        isEnabled = isAvailable
    }
}