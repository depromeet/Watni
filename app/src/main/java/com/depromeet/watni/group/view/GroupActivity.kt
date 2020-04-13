package com.depromeet.watni.group.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.base.onSingleClick
import com.depromeet.watni.databinding.ActivityGroupBinding
import com.depromeet.watni.group.GroupState
import com.depromeet.watni.group.GroupViewModel
import com.depromeet.watni.group.GroupViewModelFactory
import com.depromeet.watni.model.source.GroupRepository
import com.depromeet.watni.sign.view.LoginActivity
import com.depromeet.watni.util.SharedPrefUtil


/*
 * Created by yunji on 2020-02-22
 */
class GroupActivity : BaseActivity<ActivityGroupBinding>(R.layout.activity_group) {

    val viewModel: GroupViewModel by lazy {
        ViewModelProvider(this, GroupViewModelFactory(GroupState.NONE, GroupRepository))[GroupViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        initView()
    }

    private fun initView() {
        with(binding) {
            onSingleClick(btnGroupCreate, View.OnClickListener { startGroupFragment(GroupState.CREATE_NAME) })
            onSingleClick(btnGroupEnter, View.OnClickListener { startGroupFragment(GroupState.JOIN) })
            btnLogout.setOnClickListener {
                startActivity(LoginActivity.getIntent(this@GroupActivity))
                SharedPrefUtil.clearAll()
                finish()
            }
        }
    }

    fun startGroupFragment(groupState: GroupState) {
        startNewFragment(
            R.id.container,
            GroupFragment.newInstance(groupState),
            GroupFragment.TAG + supportFragmentManager.backStackEntryCount
        )
    }

    companion object {
        fun getIntent(context: Context?): Intent? = Intent(context, GroupActivity::class.java)
    }
}