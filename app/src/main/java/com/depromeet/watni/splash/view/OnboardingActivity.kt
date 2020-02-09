package com.depromeet.watni.splash.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.depromeet.watni.BR
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.databinding.ActivityOnboardingBinding
import com.depromeet.watni.sign.view.LoginActivity
import com.depromeet.watni.splash.adapter.OnboardingViewPagerAdapter


class OnboardingActivity :
    BaseActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {
    private val pagerAdapter by lazy {
        OnboardingViewPagerAdapter(R.layout.item_onboarding, BR.resId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding) {
            lifecycleOwner = this@OnboardingActivity
            pagerOnboarding.adapter = pagerAdapter
            pagerOnboarding.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    indicatorOnboarding.changePosition(position)
                }
            })
            btnStart.setOnClickListener {
                startActivity(LoginActivity.getIntent(this@OnboardingActivity))
                finish()
            }
        }
    }

    companion object {
        fun getIntent(context: Context?): Intent? =
            Intent(context, OnboardingActivity::class.java)
    }
}