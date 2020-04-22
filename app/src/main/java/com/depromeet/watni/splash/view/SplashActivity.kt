package com.depromeet.watni.splash.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.depromeet.watni.ext.getViewModelFactory
import com.depromeet.watni.group.view.GroupActivity
import com.depromeet.watni.home.view.HomeActivity
import com.depromeet.watni.sign.view.LoginActivity
import com.depromeet.watni.splash.SplashViewModel

class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launchFirstScreen()
        viewModel.checkAuthStatus()
    }

    private fun launchFirstScreen() {
        if (viewModel.isFirstLaunch()) {
            startActivity(OnboardingActivity.getIntent(this))
            finish()
        }

        viewModel.authStatus.observe(this, Observer {
            startActivity(getIntentByAuthStatus(it))
            finish()
        })
    }

    private fun getIntentByLoginStatus() = if (viewModel.hasGroup()) {
        HomeActivity.getIntent(this)
    } else {
        GroupActivity.getIntent(this)
    }

    private fun getIntentByAuthStatus(authStatus: Boolean) = if (authStatus) {
        getIntentByLoginStatus()
    } else {
        LoginActivity.getIntent(this)
    }
}