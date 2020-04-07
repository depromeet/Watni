package com.depromeet.watni.group.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.base.onSingleClick
import com.depromeet.watni.databinding.ActivityGroupBinding
import com.depromeet.watni.group.GroupState
import com.depromeet.watni.sign.view.LoginActivity
import com.depromeet.watni.util.SharedPrefUtil
import com.depromeet.watni.util.addNewFragment


/*
 * Created by yunji on 2020-02-22
 */
class GroupActivity : BaseActivity<ActivityGroupBinding>(R.layout.activity_group) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        initView()
    }

    private fun initView() {
        with(binding) {
            onSingleClick(btnGroupCreate, View.OnClickListener { startFragment(GroupState.CREATE_NAME) })
            onSingleClick(btnGroupEnter, View.OnClickListener { startFragment(GroupState.JOIN) })
            btnLogout.setOnClickListener {
                startActivity(LoginActivity.getIntent(this@GroupActivity))
                SharedPrefUtil.clearAll()
                finish()
            }
        }
    }

    fun startFragment(groupState: GroupState) {
        supportFragmentManager.addNewFragment(R.id.container, GroupFragment.newInstance(groupState), GroupFragment.TAG)
    }

    fun closeCurrentFragment() {
        supportFragmentManager.popBackStack()
    }

    companion object {
        fun getIntent(context: Context?): Intent? = Intent(context, GroupActivity::class.java)
    }
}