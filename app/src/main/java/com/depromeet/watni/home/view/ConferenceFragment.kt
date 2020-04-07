package com.depromeet.watni.home.view

import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseFragment
import com.depromeet.watni.databinding.FragmentConferenceBinding
import com.depromeet.watni.home.HomeViewModel

/*
 * Created by yunji on 2020-02-22
 */
class ConferenceFragment private constructor() :
    BaseFragment<FragmentConferenceBinding, HomeViewModel>(R.layout.fragment_conference) {

    override val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    companion object {

        fun getInstance() = ConferenceFragment()
    }


}