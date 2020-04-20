package com.depromeet.watni.conference

import android.content.Context
import android.content.Intent
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.databinding.ActivityEditConferenceBinding

/*
 * Created by yunji on 19/04/2020
 */
class ConferenceEditActivity : BaseActivity<ActivityEditConferenceBinding>(R.layout.activity_edit_conference) {

    companion object {
        fun getIntent(context: Context): Intent? = Intent(context, ConferenceEditActivity::class.java)
    }
}