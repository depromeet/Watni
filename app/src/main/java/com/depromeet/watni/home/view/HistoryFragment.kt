package com.depromeet.watni.home.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseFragment
import com.depromeet.watni.databinding.FragmentHistoryBinding
import com.depromeet.watni.home.HomeViewModel

/*
 * Created by yunji on 2020-02-22
 */
class HistoryFragment private constructor() :
    BaseFragment<FragmentHistoryBinding, HomeViewModel>(R.layout.fragment_history) {

    override val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    companion object {

        fun getInstance() = HistoryFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHistoryBinding.bind(view)
        initBinding()
    }
}