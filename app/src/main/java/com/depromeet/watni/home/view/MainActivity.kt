package com.depromeet.watni.home.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.databinding.ActivityMainBinding
import com.depromeet.watni.home.adapter.HomeViewPagerAdapter

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

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
        fun getIntent(context: Context?): Intent? =
            Intent(context, MainActivity::class.java)
    }
}
