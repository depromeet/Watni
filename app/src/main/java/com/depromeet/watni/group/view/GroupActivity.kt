package com.depromeet.watni.group.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.*
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.base.onSingleClick
import com.depromeet.watni.databinding.ActivityGroupBinding
import com.depromeet.watni.group.GroupViewModel
import com.depromeet.watni.sign.view.LoginActivity
import com.depromeet.watni.util.SharedPrefUtil


/*
 * Created by yunji on 2020-02-22
 */
class GroupActivity : BaseActivity<ActivityGroupBinding>(R.layout.activity_group) {
    private lateinit var viewModel: GroupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[GroupViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initView()
    }

    private fun initView() {
        with(binding) {
            onSingleClick(btnGroupCreate, View.OnClickListener { startGroupActivity(false) })
            onSingleClick(btnGroupEnter, View.OnClickListener { startGroupActivity(true) })
            btnLogout.setOnClickListener {
                startActivity(LoginActivity.getIntent(this@GroupActivity))
                SharedPrefUtil.clearAll()
                finish()
            }
        }
    }

    private fun startGroupActivity(isEntering: Boolean) {
        val intent = StartGroupActivity.getIntent(this@GroupActivity)
        intent.putExtra(EXTRA_IS_ENTERING, isEntering)
        startActivityForResult(intent, if (isEntering) START_GROUP_ENTER else START_GROUP_CREATE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == START_GROUP_SUCCESS) {
            finish()
            // Home 화면 시작
        }
    }

    companion object {
        fun getIntent(context: Context?): Intent? = Intent(context, GroupActivity::class.java)
    }
}