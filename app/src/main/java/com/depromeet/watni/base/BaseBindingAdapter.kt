package com.depromeet.watni.base

import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.depromeet.watni.R
import com.depromeet.watni.ext.convertToItemListener
import com.depromeet.watni.ext.convertToViewListener
import com.depromeet.watni.ext.disableDoubleClick
import com.depromeet.watni.ext.setUsableWithColor
import com.depromeet.watni.util.ResourceUtil

@Suppress("UNCHECKED_CAST")
@BindingAdapter("bindItem")
fun RecyclerView.bindItems(items: List<Any>?) {
    val defaultList = items ?: listOf()
    (adapter as BaseRecyclerView<*, Any>).setItems(defaultList)
}

@BindingAdapter("loadImage")
fun ImageView.loadDrawableImg(drawableResId: Int) {
    Glide.with(context)
        .load(ResourceUtil.getDrawable(drawableResId))
        .placeholder(ColorDrawable(ResourceUtil.getColor(R.color.color_light_gray)))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

@BindingAdapter("loadImage")
fun ImageView.loadUrlImg(url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(ColorDrawable(ResourceUtil.getColor(R.color.color_light_gray)))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

@BindingAdapter("loadImage")
fun ImageView.loadUriImg(uri: Uri?) {
    Glide.with(context)
        .load(uri)
        .placeholder(ColorDrawable(ResourceUtil.getColor(R.color.color_light_gray)))
        .into(this)
}

@BindingAdapter("onSingleClick")
fun Button.onSingleClick(listener: View.OnClickListener) {
    val onItemClickListener = listener.convertToItemListener()
        .disableDoubleClick()
        .convertToViewListener()

    setOnClickListener {
        onItemClickListener.onClick(it)
    }
}

@BindingAdapter("setUsable")
fun Button.setUsable(usable: Boolean) {
    setUsableWithColor(usable)
}

@BindingAdapter("setPaddingVertical")
fun View.setPaddingVertical(padding: Int) {
    setPadding(paddingLeft, padding, paddingRight, padding)
}

@BindingAdapter("setPaddingHorizontal")
fun View.setPaddingHorizontal(padding: Int) {
    setPadding(padding, paddingTop, padding, paddingBottom)
}