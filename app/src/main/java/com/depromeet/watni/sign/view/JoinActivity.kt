package com.depromeet.watni.sign.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.databinding.ActivityJoinBinding
import com.depromeet.watni.ext.updateStatus
import com.depromeet.watni.sign.JoinViewModel
import com.depromeet.watni.util.InputValidator
import com.depromeet.watni.util.showToast

/**
 * Created by yunji on 2020-01-28
 */
class JoinActivity : BaseActivity<ActivityJoinBinding>(R.layout.activity_join) {
    private lateinit var viewModel: JoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[JoinViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initView()
    }

    private fun initView() {
        with(viewModel) {
            emailText.observe(this@JoinActivity, Observer {
                binding.layoutJoinEmail.updateStatus(
                    InputValidator.isValidEmail(it),
                    R.string.join_fail_email
                )
                updateJoinAvailable()
            })
            nameText.observe(this@JoinActivity, Observer {
                binding.layoutJoinEmail.updateStatus(
                    InputValidator.isValidName(it),
                    R.string.join_fail_name
                )
                updateJoinAvailable()
            })
            pwdText.observe(this@JoinActivity, Observer {
                binding.layoutJoinPassword.updateStatus(
                    InputValidator.isValidName(it),
                    R.string.join_fail_pwd
                )
                updateJoinAvailable()
            })
            pwdConfirmText.observe(this@JoinActivity, Observer {
                binding.layoutJoinPasswordConfirm.updateStatus(
                    InputValidator.isSamePwd(pwdText.value, it),
                    R.string.login_fail_pwd
                )
                updateJoinAvailable()
            })
            joinStatus.observe(this@JoinActivity, Observer {
                finish()
            })
            msgText.observe(this@JoinActivity, Observer {
                showToast(it)
            })
        }
    }

    companion object {
        fun getIntent(context: Context?): Intent? = Intent(context, JoinActivity::class.java)
    }
}