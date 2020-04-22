package com.depromeet.watni.home.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.base.CommonStatus
import com.depromeet.watni.conference.EditMode
import com.depromeet.watni.conference.view.ConferenceEditActivity
import com.depromeet.watni.databinding.ActivityHomeBinding
import com.depromeet.watni.ext.getViewModelFactory
import com.depromeet.watni.ext.showSnack
import com.depromeet.watni.home.HomeViewModel
import com.depromeet.watni.home.adapter.HomeViewPagerAdapter
import com.depromeet.watni.listener.OnItemClickListener
import com.depromeet.watni.model.request.User
import com.depromeet.watni.network.NETWORK_ERROR_MSG
import com.depromeet.watni.util.showToast


class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    val viewModel: HomeViewModel by viewModels { getViewModelFactory() }

    private val viewPagerAdapter = HomeViewPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initView()
        observeUiData()
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initView() {
        binding.viewpagerMain.apply {
            adapter = viewPagerAdapter
            offscreenPageLimit = binding.tabMain.tabCount
        }
        binding.tabMain.apply {
            setupWithViewPager(binding.viewpagerMain)
        }
        binding.ibHomeMenu.setOnClickListener { openDrawer() }
        binding.includeDrawerHome.btnAddConference.clickListener = object : OnItemClickListener<View> {
            override fun onClick(item: View) {
                val intent = ConferenceEditActivity.getIntent(this@HomeActivity).apply {
                    putExtra(EditMode.TAG, EditMode.NEW.code)
                }
                startActivity(intent)
            }
        }
        viewModel.loadUserInfo()
    }

    private fun bindUserDetailInfo(user: User?) {
        if (user == null) {
            return
        }

        if (!user.hasConference()) {
            return
        }
    }

    private fun observeUiData() {
        viewModel.userInfo.observe(this, Observer {
            when (it.status) {
                CommonStatus.SUCCESS -> {
                    bindUserDetailInfo(it.item)
                }
                CommonStatus.ERROR -> {
                    showSnack(it.msg ?: NETWORK_ERROR_MSG)
                }
                else -> {
                    // do nothing
                }
            }
        })
        viewModel.msgTextId.observe(this, Observer { showSnack(it) })
        viewModel.toastTextId.observe(this, Observer { showToast(it) })
    }

    override fun onBackPressed() {
        if (binding.drawerHome.isDrawerOpen(GravityCompat.END)) {
            closeDrawer()
        } else {
            super.onBackPressed()
        }
    }

    private fun closeDrawer() {
        binding.drawerHome.closeDrawer(GravityCompat.END)
    }

    private fun openDrawer() {
        binding.drawerHome.openDrawer(GravityCompat.END)
    }

    private fun showSnack(resId: Int) {
        binding.layoutHomeMain.showSnack(resId)
    }

    private fun showSnack(message: String) {
        binding.layoutHomeMain.showSnack(message)
    }

    companion object {
        fun getIntent(context: Context): Intent? = Intent(context, HomeActivity::class.java)
    }
}
