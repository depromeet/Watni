package com.depromeet.watni.conference.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.conference.EditMode
import com.depromeet.watni.databinding.ActivityEditConferenceBinding
import com.depromeet.watni.listener.OnTimeRangeSelectedListener
import com.depromeet.watni.ui.TimeRangePickerDialog

/*
 * Created by yunji on 19/04/2020
 */
class ConferenceEditActivity : BaseActivity<ActivityEditConferenceBinding>(R.layout.activity_edit_conference) {
    var editMode = EditMode.NONE.code

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, ConferenceEditActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        editMode = intent.getIntExtra(EditMode.TAG, EditMode.NONE.code)
        binding.lifecycleOwner = this
        initView()
    }

    private fun initView() {
        if (editMode == EditMode.NEW.code) {
            binding.etTitle.requestFocus() // 새로 작성할 때만 키보드 바로 올라오게
        }

        binding.etTime.setOnClickListener {
            TimeRangePickerDialog.newInstance(object : OnTimeRangeSelectedListener {
                override fun onTimeSelected(startHour: Int, startMin: Int, endHour: Int, endMin: Int) {
                    binding.etTime.setTimeText(startHour, startMin, endHour, endMin)
                }
            }).show(supportFragmentManager)
        }
    }
}