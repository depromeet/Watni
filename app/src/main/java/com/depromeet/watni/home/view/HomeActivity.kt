package com.depromeet.watni.home.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.databinding.ActivityHomeBinding
import com.depromeet.watni.home.HomeViewModel
import com.depromeet.watni.home.HomeViewModelFactory
import com.depromeet.watni.home.adapter.HomeViewPagerAdapter
import com.depromeet.watni.model.source.GroupRepository

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, HomeViewModelFactory(GroupRepository))[HomeViewModel::class.java]
    }

    private val viewPagerAdapter = HomeViewPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initView()
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

    companion object {
        fun getIntent(context: Context): Intent? = Intent(context, HomeActivity::class.java)
    }
}
