package com.depromeet.watni.home.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.databinding.ActivityHomeBinding
import com.depromeet.watni.home.adapter.HomeViewPagerAdapter

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private val viewPagerAdapter = HomeViewPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
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
