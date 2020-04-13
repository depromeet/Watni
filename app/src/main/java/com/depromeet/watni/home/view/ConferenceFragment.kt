package com.depromeet.watni.home.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseFragment
import com.depromeet.watni.databinding.FragmentConferenceBinding
import com.depromeet.watni.home.HomeViewModel
import com.depromeet.watni.home.HomeViewModelFactory
import com.depromeet.watni.model.source.GroupRepository
import com.depromeet.watni.model.source.SignRepository

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
    }
}