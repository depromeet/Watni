package com.depromeet.watni.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import com.depromeet.watni.R
import com.depromeet.watni.databinding.BtnMenuItemBinding
import com.depromeet.watni.ext.disableDoubleClick
import com.depromeet.watni.listener.OnItemClickListener
import com.depromeet.watni.util.showToast


/*
 * Created by yunji on 18/04/2020
 */
class MenuItemButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    val binding: BtnMenuItemBinding = BtnMenuItemBinding.inflate(LayoutInflater.from(context))
    var clickListener: OnItemClickListener<View>? = null
        set(value) {
            field = value?.disableDoubleClick()
        }
    var arrowVisibility = true
        set(value) {
            field = value
            binding.ivArrow.visibility = if (field) View.VISIBLE else View.GONE
            invalidate()
        }
    var countViewVisibility = false
        set(value) {
            field = value
            binding.layoutCount.visibility = if (field) View.VISIBLE else View.GONE
            invalidate()
        }
    var text: String? = ""
        set(value) {
            field = value ?: "NO TEXT"
            binding.tvTitle.text = field
            invalidate()
        }

    init {
        isClickable = true
        isFocusable = true
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

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            if (clickListener == null) {
                Log.w(TAG, "OnClickListener is not set.")
                showToast("개발 안됐어요 ㅎㅎ")
            }
            clickListener?.onClick(this)
        }
        return super.dispatchTouchEvent(event)
    }

    companion object {
        val TAG = MenuItemButton::class.java.name
    }
}