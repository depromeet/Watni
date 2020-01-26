package com.depromeet.watni.splash.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.depromeet.watni.util.SharedPrefUtil
import com.depromeet.watni.view.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        decideFirstScreen()
        finish()
    }

    private fun decideFirstScreen() {
        when {
            SharedPrefUtil.isFirstLaunch() -> startActivity(OnboardingActivity.getIntent(this))
            SharedPrefUtil.getUserInfo() == null -> {
                /* TODO: 로그인 페이지 이동 */
            }
            else -> startActivity(MainActivity.getIntent(this))
        }
    }
}