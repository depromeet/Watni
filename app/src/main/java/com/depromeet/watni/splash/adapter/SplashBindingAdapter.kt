package com.depromeet.watni.splash.adapter

import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView

/*
 * Created by yunji on 2020-02-22
 */
@BindingAdapter("set_file")
fun setAssetFile(lottieAnimationView: LottieAnimationView, fileName: String) {
    lottieAnimationView.setAnimation(fileName)
}