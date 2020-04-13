package com.depromeet.watni.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

/*
 * Created by yunji on 13/04/2020
 */
@SuppressLint("DiffUtilEquals")
open class BaseDiffUtilCallback<T> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
}