package com.depromeet.watni.conference

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.databinding.ActivityEditConferenceBinding
import com.depromeet.watni.ui.TimeRangePickerDialog

/*
 * Created by yunji on 19/04/2020
 */
class ConferenceEditActivity : BaseActivity<ActivityEditConferenceBinding>(R.layout.activity_edit_conference) {

    companion object {
        fun getIntent(context: Context): Intent? = Intent(context, ConferenceEditActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.etTitle.requestFocus()
        binding.etTime.setOnClickListener {
            TimeRangePickerDialog.newInstance().show(supportFragmentManager)
        }
    }
}