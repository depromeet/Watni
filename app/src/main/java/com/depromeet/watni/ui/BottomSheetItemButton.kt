package com.depromeet.watni.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.depromeet.watni.R
import com.depromeet.watni.databinding.BtnBottomSheetItemBinding
import com.depromeet.watni.listener.OnItemClickListener
import com.depromeet.watni.listener.OnSingleClickListener


/*
 * Created by yunji on 18/04/2020
 */
class BottomSheetItemButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {
    val binding = BtnBottomSheetItemBinding.inflate(LayoutInflater.from(context))
    var btnText: String? = ""
        set(value) {
            field = value
            binding.btnCenter.text = value
            if (value.isNullOrEmpty()) {
                visibility = GONE
            }
            invalidate()
        }
    var clickListener: OnItemClickListener<View>? = null
        set(value) {
            field = if (value != null) OnSingleClickListener.wrap(value) else null
        }

    init {
        isClickable = true
        isFocusable = true
        addView(binding.root.apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        })
        setTypeArray(context, attrs)
        setOnClickListener(this)
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

    override fun onClick(v: View) {
        Log.w(TAG, "OnClickListener is not set.")
        clickListener?.onClick(v)
    }

    companion object {
        val TAG = BottomSheetItemButton::class.java.name
    }
}