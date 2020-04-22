package com.depromeet.watni.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.depromeet.watni.R
import com.depromeet.watni.databinding.BtnBottomSheetItemBinding


/*
 * Created by yunji on 18/04/2020
 */
class BottomSheetItemButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding = BtnBottomSheetItemBinding.inflate(LayoutInflater.from(context))
    var btnText: String? = ""
        set(value) {
            field = value
            binding.btnCenter.text = value
            visibility = if (value.isNullOrEmpty()) View.GONE else View.VISIBLE
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

    fun setBtnClickListener(onClick: (view: View) -> Unit) {
        binding.btnCenter.setOnClickListener { run(onClick) }
    }

    private fun setTypeArray(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomSheetItemButton)
        for (i in 0 until typedArray.indexCount) {
            when (i) {
                R.styleable.BottomSheetItemButton_android_text ->
                    btnText = typedArray.getString(typedArray.getIndex(i))
            }
        }
        typedArray.recycle()
    }
}