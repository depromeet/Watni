package com.depromeet.watni.home.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseFragment
import com.depromeet.watni.base.CommonStatus
import com.depromeet.watni.conference.EditMode
import com.depromeet.watni.conference.view.ConferenceEditActivity
import com.depromeet.watni.databinding.FragmentConferenceBinding
import com.depromeet.watni.ext.getViewModelFactory
import com.depromeet.watni.home.HomeViewModel
import com.depromeet.watni.home.adapter.ConferenceRecyclerAdapter
import com.depromeet.watni.model.request.User

/*
 * Created by yunji on 2020-02-22
 */
class ConferenceFragment :
    BaseFragment<FragmentConferenceBinding, HomeViewModel>(R.layout.fragment_conference) {
    private val conferenceAdapter = ConferenceRecyclerAdapter()

    override val viewModel: HomeViewModel by activityViewModels { getViewModelFactory() }

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
        binding.noConferenceManager.btnConferenceCreate.setOnClickListener {
            ConferenceEditActivity.getIntent(requireContext()).apply {
                putExtra(EditMode.TAG, EditMode.NEW.code)
            }.also {
                startActivityForResult(it, EditMode.NEW.code)
            }
        }
    }

    private fun bindUserDetailInfo(user: User?) {
        if (user == null) {
            return
        }

        binding.apply {
            layoutConferenceExist.visibility = View.VISIBLE
            noConferenceManager.layoutNoConference.visibility =
                if (!user.hasConference() && user.isManager()) View.VISIBLE else View.GONE
            noConferenceMember.layoutNoConference.visibility =
                if (!user.hasConference() && !user.isManager()) View.VISIBLE else View.GONE
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