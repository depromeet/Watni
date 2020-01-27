package com.depromeet.watni.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("bind_item")
fun bindItems(rv: RecyclerView, data: List<Any>) {
    (rv.adapter as BaseRecyclerView<*, Any>).setItems(data)
}

@BindingAdapter("bind_img")
fun loadImage(imageView: ImageView, resId: Int) {
    imageView.setImageResource(resId)
}
