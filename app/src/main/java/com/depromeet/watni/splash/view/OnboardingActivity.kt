package com.depromeet.watni.splash.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.databinding.ActivitySplashBinding


class OnboardingActivity :
    BaseActivity<ActivitySplashBinding>(R.layout.activity_onboarding) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {

    }

    companion object {
        fun getIntent(context: Context?): Intent? =
            Intent(context, OnboardingActivity::class.java)
    }
}