package com.depromeet.watni.ui

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.depromeet.watni.R
import com.depromeet.watni.util.DateTimeUtil
import java.util.*


/*
 * Created by yunji on 20/04/2020
 */
class DateEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr), View.OnClickListener {
    var currentDate: Calendar = Calendar.getInstance()

    init {
        isClickable = true
        setText(DateTimeUtil.getTodayString())
        setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        showDatePickerDialog()
    }

    fun getTimestamp() = currentDate.time.time

    private fun showDatePickerDialog() {
        val calendar: Calendar = Calendar.getInstance()
        DatePickerDialog(
            context,
            R.style.DialogStyle,
            OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                currentDate = Calendar.getInstance().apply { set(year, monthOfYear, dayOfMonth) }
                setText(DateTimeUtil.getTodayString(currentDate))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.minDate = System.currentTimeMillis()
        }.show()
    }
}