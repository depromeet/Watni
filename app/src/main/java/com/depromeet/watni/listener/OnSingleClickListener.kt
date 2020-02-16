package com.depromeet.watni.listener

import android.view.View

abstract class OnSingleClickListener<T>(
    private val clickDelayMilliSeconds: Long = MIN_PREVIEW_MULTIPLE_CLICK_DELAY_MS
) : OnItemClickListener<T> {
    private var lastClickTime = 0L

    override fun onClick(item: T) {
        val now = System.currentTimeMillis()
        val clickGap = now - lastClickTime
        if (clickGap > clickDelayMilliSeconds) {
            onSingleClick(item)
            lastClickTime = now
        }
    }

    abstract fun onSingleClick(item: T)

    companion object {
        private const val MIN_PREVIEW_MULTIPLE_CLICK_DELAY_MS = 500L

        fun <T> wrap(
            listener: OnItemClickListener<T>,
            clickDelayMilliSeconds: Long = MIN_PREVIEW_MULTIPLE_CLICK_DELAY_MS
        ): OnItemClickListener<T> {
            return object : OnSingleClickListener<T>(clickDelayMilliSeconds) {
                override fun onSingleClick(item: T) {
                    listener.onClick(item)
                }
            }
        }

        fun convertToViewClickListener(
            listener: OnSingleClickListener<View>,
            clickDelayMilliSeconds: Long = MIN_PREVIEW_MULTIPLE_CLICK_DELAY_MS
        ): View.OnClickListener {
            return View.OnClickListener {
                listener.onClick(it)
            }
        }
    }
}