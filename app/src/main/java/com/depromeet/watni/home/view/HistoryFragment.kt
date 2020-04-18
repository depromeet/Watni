package com.depromeet.watni.home.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseFragment
import com.depromeet.watni.base.CommonStatus
import com.depromeet.watni.databinding.FragmentHistoryBinding
import com.depromeet.watni.home.HomeViewModel
import com.depromeet.watni.home.HomeViewModelFactory
import com.depromeet.watni.model.request.User
import com.depromeet.watni.model.source.GroupRepository
import com.depromeet.watni.model.source.SignRepository

/*
 * Created by yunji on 2020-02-22
 */
class HistoryFragment private constructor() :
    BaseFragment<FragmentHistoryBinding, HomeViewModel>(R.layout.fragment_history) {

    override val viewModel: HomeViewModel by lazy {
        ViewModelProvider(
            getViewModelStoreOwner(),
            HomeViewModelFactory(SignRepository, GroupRepository)
        )[HomeViewModel::class.java]
    }

    companion object {

        fun getInstance() = HistoryFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHistoryBinding.bind(view)
        initBinding()
        initView()
        observeUiData()
    }

    private fun initView() {

    }

    private fun bindUserDetailInfo(user: User?) {
        binding.apply {

        }
    }

    private fun observeUiData() {
        viewModel.userInfo.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                CommonStatus.SUCCESS -> {
                    bindUserDetailInfo(it.item)
                }
                else -> {
                    // do nothing
                }
            }
        })
    }
}