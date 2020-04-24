package com.depromeet.watni.sign.adapter

import android.widget.Button
import androidx.databinding.BindingAdapter
import com.depromeet.watni.ext.setUsableWithBackground

@BindingAdapter("setEnable")
fun Button.setJoinBtnEnable(isAvailable: Boolean) {
    setUsableWithBackground(isAvailable)
}