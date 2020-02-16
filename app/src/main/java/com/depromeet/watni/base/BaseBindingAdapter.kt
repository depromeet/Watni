package com.depromeet.watni.base

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.watni.listener.OnSingleClickListener

@Suppress("UNCHECKED_CAST")
@BindingAdapter("bindItem")
fun bindItems(rv: RecyclerView, data: List<Any>) {
    (rv.adapter as BaseRecyclerView<*, Any>).setItems(data)
}

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, resId: Int) {
    imageView.setImageResource(resId)
}

@BindingAdapter("onSingleClick")
fun onSingleClick(button: Button, listener: View.OnClickListener) {
    button.setOnClickListener {
        OnSingleClickListener.convertToViewClickListener(object : OnSingleClickListener<View>() {
            override fun onSingleClick(item: View) {
                listener.onClick(item)
            }
        })
    }
}