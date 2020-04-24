package com.depromeet.watni.ext

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.depromeet.watni.R
import com.depromeet.watni.base.loadUriImg
import com.depromeet.watni.ui.model.BasicButtonContent
import com.depromeet.watni.util.ResourceUtil

/*
 * Created by yunji on 21/04/2020
 */
fun Button.setUsableWithColor(
    usable: Boolean,
    @ColorRes usableTextColorId: Int = android.R.color.black,
    @ColorRes disableTextColorId: Int = R.color.color_4D000000
) {
    isEnabled = usable
    setTextColor(ResourceUtil.getColor(if (usable) usableTextColorId else disableTextColorId))
}

fun Button.setUsableWithBackground(
    usable: Boolean,
    @ColorRes usableTextColorId: Int = android.R.color.black,
    @ColorRes disableTextColorId: Int = R.color.color_4D000000,
    @DrawableRes usableBackDrawableId: Int = R.drawable.shape_rect_stroke_red,
    @DrawableRes disableBackDrawableId: Int = R.drawable.shape_rect_stroke_gray
) {
    isEnabled = usable
    setTextColor(ResourceUtil.getColor(if (usable) usableTextColorId else disableTextColorId))
    setBackgroundResource(if (usable) usableBackDrawableId else disableBackDrawableId)
}

fun Button.bindBasicContent(basicButtonContent: BasicButtonContent?) {
    if (basicButtonContent == null) {
        visibility = View.GONE
        return
    }

    text = basicButtonContent.text
    setOnClickListener { run(basicButtonContent.onClick) }
}

fun ImageButton.setFullImage(bitmap: Bitmap?) {
    if (bitmap == null) {
        Log.w("setFullImage", "Bitmap is null.")
        return
    }

    scaleType = ImageView.ScaleType.CENTER_CROP
    setImageBitmap(bitmap)
}

fun ImageButton.setFullImage(uri: Uri?) {
    if (uri == null) {
        Log.w("setFullImage", "Uri is null.")
        return
    }

    scaleType = ImageView.ScaleType.CENTER_CROP
    loadUriImg(uri)
}