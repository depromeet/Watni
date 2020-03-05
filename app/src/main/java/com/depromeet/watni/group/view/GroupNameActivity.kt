package com.depromeet.watni.group.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.R
import com.depromeet.watni.START_GROUP_SUCCESS
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.databinding.FragmentJoinGroupBinding
import com.depromeet.watni.group.GroupViewModel
import com.depromeet.watni.util.showToast


/*
 * Created by yunji on 2020-02-22
 */
class GroupNameActivity : BaseActivity<FragmentJoinGroupBinding>(R.layout.fragment_join_group) {
    private lateinit var viewModel: GroupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[GroupViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initView()
        initViewModel()
    }

    private fun initView() {
        viewModel.groupName.observe(this, Observer {
            viewModel.checkOkButtonEnable()
        })
    }

    private fun initViewModel() {
        with(viewModel) {
            invitationCode.observe(this@GroupNameActivity, Observer {
                checkNextButtonEnable()
            })
            status.observe(this@GroupNameActivity, Observer {
                showToast("새로운 모임을 만들었습니다.")
                setResult(START_GROUP_SUCCESS)
                finish()
            })
        }
    }

    companion object {
        fun getIntent(context: Context?): Intent = Intent(context, GroupNameActivity::class.java)
    }
}