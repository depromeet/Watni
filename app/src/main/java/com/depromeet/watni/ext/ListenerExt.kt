package com.depromeet.watni.ext

import android.view.View
import com.depromeet.watni.listener.OnItemClickListener
import com.depromeet.watni.listener.OnSingleClickListener

/*
 * Created by yunji on 22/04/2020
 */
fun <T> OnItemClickListener<T>.disableDoubleClick(
    clickDelayMilliSeconds: Long = OnSingleClickListener.MIN_CLICK_DELAY_MS
): OnItemClickListener<T> {
    return object : OnSingleClickListener<T>(clickDelayMilliSeconds) {
        override fun onSingleClick(item: T) {
            this@disableDoubleClick.onClick(item)
        }
    }
}

fun OnSingleClickListener<View>.convertToViewClickListener() = View.OnClickListener {
    this.onClick(it)
}