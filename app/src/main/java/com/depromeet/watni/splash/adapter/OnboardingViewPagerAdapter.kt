package com.depromeet.watni.splash.adapter

import androidx.annotation.LayoutRes
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseRecyclerView
import com.depromeet.watni.databinding.ItemOnboardingBinding
import okhttp3.internal.immutableListOf

class OnboardingViewPagerAdapter(
    @LayoutRes private val layoutResId: Int,
    bindingVariableId: Int
) : BaseRecyclerView<ItemOnboardingBinding, Int>(layoutResId, bindingVariableId) {

    init {
        // TODO : 온보딩 일러스트 아이디로 변경
        setItems(immutableListOf(R.drawable.test, R.drawable.test, R.drawable.test))
    }
}