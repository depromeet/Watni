package com.depromeet.watni.sign.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.databinding.ActivityLoginBinding
import com.depromeet.watni.sign.LoginViewModel

/**
 * Created by yunji on 2020-01-28
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding) {
            btnJoin.setOnClickListener { startActivity(JoinActivity.getIntent(this@LoginActivity)) }
        }
    }

    companion object {
        fun getIntent(context: Context?): Intent? = Intent(context, LoginActivity::class.java)
    }
}