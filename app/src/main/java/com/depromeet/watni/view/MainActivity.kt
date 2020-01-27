package com.depromeet.watni.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseActivity
import com.depromeet.watni.databinding.ActivityMainBinding
import com.depromeet.watni.splash.view.OnboardingActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        fun getIntent(context: Context?): Intent? =
            Intent(context, OnboardingActivity::class.java)
    }
}
