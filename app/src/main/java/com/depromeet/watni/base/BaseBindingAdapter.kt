package com.depromeet.watni.base

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.depromeet.watni.R
import com.depromeet.watni.ext.convertToViewClickListener
import com.depromeet.watni.listener.OnSingleClickListener
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

@BindingAdapter("onSingleClick")
fun Button.onSingleClick(listener: View.OnClickListener) {
    setOnClickListener {
        object : OnSingleClickListener<View>() {
            override fun onSingleClick(item: View) {
                listener.onClick(it) // 중복 클릭 방지
            }
        }.convertToViewClickListener()
    }
}

@BindingAdapter("setPaddingVertical")
fun View.setPaddingVertical(padding: Int) {
    setPadding(paddingLeft, padding, paddingRight, padding)
}

@BindingAdapter("setPaddingHorizontal")
fun View.setPaddingHorizontal(padding: Int) {
    setPadding(padding, paddingTop, padding, paddingBottom)
}