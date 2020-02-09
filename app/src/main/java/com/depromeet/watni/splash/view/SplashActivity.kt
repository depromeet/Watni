package com.depromeet.watni.splash.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.depromeet.watni.sign.view.LoginActivity
import com.depromeet.watni.util.SharedPrefUtil
import com.depromeet.watni.view.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launchFirstScreen()
        finish()
    }

    private fun launchFirstScreen() {
        when {
            SharedPrefUtil.isFirstLaunch() -> startActivity(OnboardingActivity.getIntent(this))
            SharedPrefUtil.isLoggedIn() -> startActivity(MainActivity.getIntent(this))
            else -> startActivity(LoginActivity.getIntent(this))
        }
    }
}