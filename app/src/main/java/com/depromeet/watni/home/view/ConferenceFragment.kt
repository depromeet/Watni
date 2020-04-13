package com.depromeet.watni.home.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseFragment
import com.depromeet.watni.base.CommonStatus
import com.depromeet.watni.databinding.FragmentConferenceBinding
import com.depromeet.watni.home.HomeViewModel
import com.depromeet.watni.home.HomeViewModelFactory
import com.depromeet.watni.model.request.User
import com.depromeet.watni.model.source.GroupRepository
import com.depromeet.watni.model.source.SignRepository
import com.depromeet.watni.util.ResourceUtil

/*
 * Created by yunji on 2020-02-22
 */
class ConferenceFragment private constructor() :
    BaseFragment<FragmentConferenceBinding, HomeViewModel>(R.layout.fragment_conference) {

    override val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, HomeViewModelFactory(SignRepository, GroupRepository))[HomeViewModel::class.java]
    }

    companion object {

        fun getInstance() = ConferenceFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentConferenceBinding.bind(view)
        initBinding()
        initView()
        observeUiData()
    }

    private fun initView() {
        viewModel.loadUserInfo()
    }

    private fun bindUserDetailInfo(user: User?) {
        if (user == null) {
            return
        }

        binding.apply {
            tvWelcome.text = ResourceUtil.getRandomWelcomeString(user.name)
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