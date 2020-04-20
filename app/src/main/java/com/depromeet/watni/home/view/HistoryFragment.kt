package com.depromeet.watni.home.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseFragment
import com.depromeet.watni.base.CommonStatus
import com.depromeet.watni.databinding.FragmentHistoryBinding
import com.depromeet.watni.ext.getViewModelFactory
import com.depromeet.watni.home.HomeViewModel
import com.depromeet.watni.model.request.User

/*
 * Created by yunji on 2020-02-22
 */
class HistoryFragment :
    BaseFragment<FragmentHistoryBinding, HomeViewModel>(R.layout.fragment_history) {

    override val viewModel: HomeViewModel by activityViewModels { getViewModelFactory() }

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