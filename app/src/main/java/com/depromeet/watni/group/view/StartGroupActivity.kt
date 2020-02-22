package com.depromeet.watni.group.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.EXTRA_IS_ENTERING
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.databinding.ActivityStartGroupBinding
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
    }

    private fun initView() {
        viewModel.inputText.observe(this, Observer {
            viewModel.checkButtonEnable()
        })
    }

    companion object {
        fun getIntent(context: Context?): Intent = Intent(context, StartGroupActivity::class.java)
    }
}