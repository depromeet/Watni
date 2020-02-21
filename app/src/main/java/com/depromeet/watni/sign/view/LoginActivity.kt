package com.depromeet.watni.sign.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.databinding.ActivityLoginBinding
import com.depromeet.watni.sign.LoginViewModel
import com.depromeet.watni.util.showToast

/**
 * Created by yunji on 2020-01-28
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        initView()
    }

    private fun initView() {
        with(viewModel) {
            binding.btnJoin.setOnClickListener { startActivity(JoinActivity.getIntent(this@LoginActivity)) }
            emailText.observe(this@LoginActivity, Observer { updateLoginAvailable() })
            pwdText.observe(this@LoginActivity, Observer { updateLoginAvailable() })
            loginStatus.observe(this@LoginActivity, Observer {
                // TODO : 모임 화면으로 이동
            })
            msgText.observe(this@LoginActivity, Observer { showToast(it) })
        }
    }

    companion object {
        fun getIntent(context: Context?): Intent? = Intent(context, LoginActivity::class.java)
    }
}