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
import com.depromeet.watni.home.adapter.ConferenceRecyclerAdapter
import com.depromeet.watni.model.request.User
import com.depromeet.watni.model.source.GroupRepository
import com.depromeet.watni.model.source.SignRepository
import com.depromeet.watni.util.ResourceUtil

/*
 * Created by yunji on 2020-02-22
 */
class ConferenceFragment private constructor() :
    BaseFragment<FragmentConferenceBinding, HomeViewModel>(R.layout.fragment_conference) {
    private val conferenceAdapter = ConferenceRecyclerAdapter()

    override val viewModel: HomeViewModel by lazy {
        ViewModelProvider(
            getViewModelStoreOwner(),
            HomeViewModelFactory(SignRepository, GroupRepository)
        )[HomeViewModel::class.java]
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

    override fun initBinding() {
        super.initBinding()
        binding.rvConference.adapter = conferenceAdapter
    }

    private fun initView() {
        viewModel.loadUserInfo()
    }

    private fun bindUserDetailInfo(user: User?) {
        if (user == null) {
            return
        }

        val isManager = user.memberDetails[0].manager
        val groupInfo = user.memberDetails[0].group

        binding.apply {
            tvWelcome.text = ResourceUtil.getRandomWelcomeString(user.name)

            if (groupInfo.conferences.isNotEmpty()) {
                layoutConferenceExist.visibility = View.VISIBLE
                noConferenceManager.layoutNoConference.visibility = View.GONE
                noConferenceMember.layoutNoConference.visibility = View.GONE
            } else {
                layoutConferenceExist.visibility = View.GONE
                noConferenceManager.layoutNoConference.visibility = if (isManager) View.VISIBLE else View.GONE
                noConferenceMember.layoutNoConference.visibility = if (!isManager) View.VISIBLE else View.GONE
            }
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