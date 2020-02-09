package com.depromeet.watni.sign.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.databinding.ActivityLoginBinding
import com.depromeet.watni.sign.JoinViewModel

/**
 * Created by yunji on 2020-01-28
 */
class JoinActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_join) {
    private val viewModel by lazy {
        ViewModelProvider(this)[JoinViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
    }

    companion object {
        fun getIntent(context: Context?): Intent? = Intent(context, JoinActivity::class.java)
    }
}