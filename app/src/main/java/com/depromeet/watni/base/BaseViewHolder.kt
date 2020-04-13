package com.depromeet.watni.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.watni.BR
import com.depromeet.watni.listener.OnItemClickListener

abstract class BaseViewHolder<B : ViewDataBinding, T>(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup?
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent?.context).inflate(layoutResId, parent, false)
) {
    private val binding: B = DataBindingUtil.bind(itemView)!!

    fun bind(item: T, listener: OnItemClickListener<T>) {
        try {
            binding.run {
                setVariable(BR.item, item)
                // setVariable(BR.listener, listener)
                executePendingBindings()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}