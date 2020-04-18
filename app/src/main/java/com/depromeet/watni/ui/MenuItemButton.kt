package com.depromeet.watni.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.depromeet.watni.R
import com.depromeet.watni.databinding.BtnMenuItemBinding


/*
 * Created by yunji on 18/04/2020
 */
class MenuItemButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    val binding: BtnMenuItemBinding = BtnMenuItemBinding.inflate(LayoutInflater.from(context))
    var arrowVisibility = true
        set(value) {
            field = value
            binding.ivArrow.visibility = if (arrowVisibility) View.VISIBLE else View.GONE
            invalidate()
        }
    var countViewVisibility = false
        set(value) {
            field = value
            binding.layoutCount.visibility = if (arrowVisibility) View.VISIBLE else View.GONE
            invalidate()
        }
    var text: String? = ""
        set(value) {
            field = value ?: "NO TEXT"
            binding.tvTitle.text = value
            invalidate()
        }

    init {
        addView(binding.root.apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        })

        setTypeArray(context, attrs)
    }

    private fun setTypeArray(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MenuItemButton)
        for (i in 0 until typedArray.indexCount) {
            when (i) {
                R.styleable.MenuItemButton_arrowVisibility ->
                    arrowVisibility = typedArray.getBoolean(typedArray.getIndex(i), arrowVisibility)
                R.styleable.MenuItemButton_countViewVisibility ->
                    countViewVisibility = typedArray.getBoolean(typedArray.getIndex(i), countViewVisibility)
                R.styleable.MenuItemButton_text ->
                    text = typedArray.getString(typedArray.getIndex(i))
            }
        }
        typedArray.recycle()
    }
}