package com.depromeet.watni.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.depromeet.watni.R
import com.depromeet.watni.util.dpToPx


class CircleIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var dotImages: MutableList<View> = mutableListOf()
    private var position = 0
    private var count = 0
    var defaultCircle: Int = R.drawable.shape_circle_solid_gray
    var selectedCircle: Int = R.drawable.shape_circle_solid_black
    var circleMargin = dpToPx(4f)
        set(value) {
            field = if (value < 0f) 0 else dpToPx(value.toFloat()) / 2
            invalidate()
        }
    var circleSize = dpToPx(10f)
        set(value) {
            field = if (value < 0f) 0 else dpToPx(value.toFloat())
            invalidate()
        }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleIndicator)
        for (i in 0 until typedArray.indexCount) {
            when (i) {
                R.styleable.CircleIndicator_count ->
                    count = typedArray.getInt(typedArray.getIndex(i), count)
                R.styleable.CircleIndicator_defaultPosition ->
                    position = typedArray.getInt(typedArray.getIndex(i), position)
            }
        }
        typedArray.recycle()
        initPanel()
    }

    private fun initPanel() {
        removeAllViews()
        for (i in 0 until count) {
            dotImages.add(View(context).apply {
                layoutParams = LayoutParams(circleSize, circleSize).apply {
                    marginEnd = circleMargin
                    marginStart = circleMargin
                }
            })
            addView(dotImages[i])
        }
        changePosition(position)
    }

    fun changePosition(position: Int) {
        for (i in dotImages.indices) {
            dotImages[i].setBackgroundResource(if (i == position) selectedCircle else defaultCircle)
        }
    }
}