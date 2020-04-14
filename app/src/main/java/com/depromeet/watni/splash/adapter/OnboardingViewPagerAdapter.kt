package com.depromeet.watni.splash.adapter

import androidx.annotation.LayoutRes
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseRecyclerView
import com.depromeet.watni.databinding.ItemOnboardingBinding
import com.depromeet.watni.ui.model.OnboardingItem
import com.depromeet.watni.util.ResourceUtil
import okhttp3.internal.immutableListOf

class OnboardingViewPagerAdapter(
    @LayoutRes private val layoutResId: Int
) : BaseRecyclerView<ItemOnboardingBinding, OnboardingItem>(layoutResId) {

    init {
        val msgArray = ResourceUtil.getStringArray(R.array.onboarding_msg)
        setItems(
            immutableListOf(
                OnboardingItem("splash1.json", msgArray[0], msgArray[1]),
                OnboardingItem("splash2.json", msgArray[2], msgArray[3]),
                OnboardingItem("splash2.json", "", "")
            )
        )
    }
}