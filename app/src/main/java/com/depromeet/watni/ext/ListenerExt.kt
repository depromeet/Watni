package com.depromeet.watni.ext

import android.view.View
import com.depromeet.watni.listener.OnItemClickListener
import com.depromeet.watni.listener.OnSingleClickListener

/*
 * Created by yunji on 22/04/2020
 */
const val MIN_CLICK_DELAY_MS = 500L

fun <T> OnItemClickListener<T>.disableDoubleClick(
    clickDelayMilliSeconds: Long = MIN_CLICK_DELAY_MS
): OnItemClickListener<T> = object : OnSingleClickListener<T>(clickDelayMilliSeconds) {
    override fun onSingleClick(item: T) {
        this@disableDoubleClick.onClick(item)
    }
}

fun OnItemClickListener<View>.convertToViewListener(
    clickDelayMilliSeconds: Long = MIN_CLICK_DELAY_MS
): View.OnClickListener = View.OnClickListener {
    this.onClick(it)
}

fun View.OnClickListener.convertToItemListener(
    clickDelayMilliSeconds: Long = MIN_CLICK_DELAY_MS
): OnItemClickListener<View> = object : OnItemClickListener<View> {
    override fun onClick(item: View) {
        this@convertToItemListener.onClick(item)
    }
}