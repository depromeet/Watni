package com.depromeet.watni.home.view

import com.depromeet.watni.R
import com.depromeet.watni.base.BaseFragment
import com.depromeet.watni.databinding.FragmentConferenceBinding

/*
 * Created by yunji on 2020-02-22
 */
class ConferenceFragment private constructor() : BaseFragment<FragmentConferenceBinding>(R.layout.fragment_conference) {

    companion object {

        fun getInstance() = ConferenceFragment()
    }
}