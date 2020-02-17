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
import com.depromeet.watni.listener.OnSingleClickListener
import com.depromeet.watni.util.ResourceUtil

@Suppress("UNCHECKED_CAST")
@BindingAdapter("bindItem")
fun bindItems(rv: RecyclerView, data: List<Any>) {
    (rv.adapter as BaseRecyclerView<*, Any>).setItems(data)
}

@BindingAdapter("loadImage")
fun loadDrawableImg(imageView: ImageView, drawableResId: Int) {
    Glide.with(imageView.context)
        .load(ResourceUtil.getDrawable(drawableResId))
        .placeholder(ColorDrawable(ResourceUtil.getColor(R.color.color_gray)))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}

@BindingAdapter("loadImage")
fun loadUrlImg(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(ColorDrawable(ResourceUtil.getColor(R.color.color_gray)))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}

@BindingAdapter("onSingleClick")
fun onSingleClick(button: Button, listener: View.OnClickListener) {
    button.setOnClickListener(
        OnSingleClickListener.convertToViewClickListener(object : OnSingleClickListener<View>() {
            override fun onSingleClick(item: View) {
                listener.onClick(item) // 중복 클릭 방지
            }
        })
    )
}

@BindingAdapter("setPaddingVertical")
fun setPaddingVertical(view: View, padding: Int) {
    view.setPadding(view.paddingLeft, padding, view.paddingRight, padding)
}

@BindingAdapter("setPaddingHorizontal")
fun setPaddingHorizontal(view: View, padding: Int) {
    view.setPadding(padding, view.paddingTop, padding, view.paddingBottom)
}