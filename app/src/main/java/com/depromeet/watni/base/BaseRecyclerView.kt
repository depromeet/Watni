package com.depromeet.watni.base

import android.util.Log
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.watni.listener.OnItemClickListener

abstract class BaseRecyclerView<B : ViewDataBinding, T : Any>(
    @LayoutRes private val layoutResId: Int,
    private val bindingVariableId: Int
) : RecyclerView.Adapter<BaseViewHolder<B, T>>() {
    protected val items = mutableListOf<T>()

    var clickListener = object : OnItemClickListener<T> {
        override fun onClick(item: T) {
            Log.d("list item clicked", item.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        object : BaseViewHolder<B, T>(layoutResId, parent) {}

    override fun getItemCount(): Int = items.size

    fun getItem(position: Int) = items[position]

    fun setItems(newItems: List<T>) {
        val diffUtil = BaseDiffUtil(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<B, T>, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}