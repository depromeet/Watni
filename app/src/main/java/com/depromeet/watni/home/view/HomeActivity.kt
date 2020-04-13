package com.depromeet.watni.home.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.base.CommonStatus
import com.depromeet.watni.databinding.ActivityHomeBinding
import com.depromeet.watni.ext.showSnack
import com.depromeet.watni.home.HomeViewModel
import com.depromeet.watni.home.HomeViewModelFactory
import com.depromeet.watni.home.adapter.HomeViewPagerAdapter
import com.depromeet.watni.model.request.User
import com.depromeet.watni.model.source.GroupRepository
import com.depromeet.watni.model.source.SignRepository
import com.depromeet.watni.network.NETWORK_ERROR_MSG
import com.depromeet.watni.util.showToast

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, HomeViewModelFactory(SignRepository, GroupRepository))[HomeViewModel::class.java]
    }

    private val viewPagerAdapter = HomeViewPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initView()
        observeUiData()
    }

    private fun initBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initView() {
        binding.viewpagerMain.apply {
            adapter = viewPagerAdapter
            offscreenPageLimit = binding.tabMain.tabCount
        }
        binding.tabMain.apply {
            setupWithViewPager(binding.viewpagerMain)
        }
    }

    private fun bindUserDetailInfo(user: User?) {
        if (user == null) {
            return
        }

        if (user.memberDetails.isEmpty()) {
            return
        }

        val groupInfo = user.memberDetails[0].group
        binding.apply {
            tvHomeTitle.text = groupInfo.name
        }
    }

    private fun observeUiData() {
        viewModel.userInfo.observe(this, Observer {
            when (it.status) {
                CommonStatus.SUCCESS -> {
                    bindUserDetailInfo(it.item)
                }
                CommonStatus.ERROR -> {
                    binding.layoutHomeMain.showSnack(it.msg ?: NETWORK_ERROR_MSG)
                }
                else -> {
                    // do nothing
                }
            }
        })
        viewModel.msgTextId.observe(this, Observer { showSnack(it) })
        viewModel.toastTextId.observe(this, Observer { showToast(it) })
    }

    private fun showSnack(resId: Int) {
        binding.layoutHomeMain.showSnack(resId)
    }

    companion object {
        fun getIntent(context: Context): Intent? = Intent(context, HomeActivity::class.java)
    }
}
