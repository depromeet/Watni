package com.depromeet.watni.splash.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.group.view.GroupActivity
import com.depromeet.watni.home.view.HomeActivity
import com.depromeet.watni.sign.view.LoginActivity
import com.depromeet.watni.splash.SplashViewModel

class SplashActivity : AppCompatActivity() {
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        launchFirstScreen()
        viewModel.checkAuthStatus()
    }

    private fun launchFirstScreen() {
        if (viewModel.isFirstLaunch()) {
            startActivity(OnboardingActivity.getIntent(this))
            finish()
        } else if (viewModel.isLoggedIn()) {
            startActivity(if (viewModel.hasGroup()) HomeActivity.getIntent(this) else GroupActivity.getIntent(this))
            finish()
        }

        viewModel.authStatus.observe(this, Observer {
            startActivity(if (it) HomeActivity.getIntent(this) else LoginActivity.getIntent(this))
            finish()
        })
    }
}