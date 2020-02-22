package com.depromeet.watni.group.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.EXTRA_IS_ENTERING
import com.depromeet.watni.R
import com.depromeet.watni.START_GROUP_CREATE
import com.depromeet.watni.START_GROUP_SUCCESS
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.databinding.ActivityStartGroupBinding
import com.depromeet.watni.ext.showMessage
import com.depromeet.watni.group.GroupViewModel


/*
 * Created by yunji on 2020-02-22
 */
class StartGroupActivity : BaseActivity<ActivityStartGroupBinding>(R.layout.activity_start_group) {
    private lateinit var viewModel: GroupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[GroupViewModel::class.java]
        binding.lifecycleOwner = this
        binding.isEntering = intent.getBooleanExtra(EXTRA_IS_ENTERING, false)
        binding.viewModel = viewModel

        initView()
        initViewModel()
    }

    private fun initView() {
        with(binding) {
            binding.btnOk.setOnClickListener {
                if (isEntering!!) {
                    viewModel!!.enterGroup()
                } else {
                    val intent = GroupNameActivity.getIntent(this@StartGroupActivity)
                    startActivityForResult(intent, START_GROUP_CREATE)
                }
            }
        }
    }

    private fun initViewModel() {
        with(viewModel) {
            invitationCode.observe(this@StartGroupActivity, Observer {
                checkNextButtonEnable()
            })
            msgText.observe(this@StartGroupActivity, Observer {
                binding.textInputLayout.showMessage(R.string.group_already_used_code)
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("result" + resultCode)
        if (resultCode == START_GROUP_SUCCESS) {
            setResult(START_GROUP_SUCCESS)
            finish()
        }
    }

    companion object {
        fun getIntent(context: Context?): Intent = Intent(context, StartGroupActivity::class.java)
    }
}