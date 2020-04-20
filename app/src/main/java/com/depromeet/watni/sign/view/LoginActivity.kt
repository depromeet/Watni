package com.depromeet.watni.sign.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.databinding.ActivityLoginBinding
import com.depromeet.watni.ext.getViewModelFactory
import com.depromeet.watni.group.view.GroupActivity
import com.depromeet.watni.home.view.HomeActivity
import com.depromeet.watni.model.request.User
import com.depromeet.watni.sign.LoginViewModel
import com.depromeet.watni.util.showToast

/**
 * Created by yunji on 2020-01-28
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel: LoginViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        initView()
    }

    private fun initView() {
        with(viewModel) {
            binding.btnJoin.setOnClickListener { startActivity(JoinActivity.getIntent(this@LoginActivity)) }
            binding.btnJoinForgotPwd.setOnClickListener { showToast("개발 중입니다") }
            emailText.observe(this@LoginActivity, Observer { updateLoginAvailable() })
            pwdText.observe(this@LoginActivity, Observer { updateLoginAvailable() })
            msgText.observe(this@LoginActivity, Observer { showToast(it) })
            loginStatus.observe(this@LoginActivity, Observer { startNextActivity(it) })
        }
    }

    private fun startNextActivity(user: User) {
        val intent = if (user.memberDetails.isEmpty()) GroupActivity.getIntent(this) else HomeActivity.getIntent(this)
        startActivity(intent)
        finish()
    }

    companion object {
        fun getIntent(context: Context?): Intent? = Intent(context, LoginActivity::class.java)
    }
}