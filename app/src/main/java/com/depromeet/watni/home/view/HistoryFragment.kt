package com.depromeet.watni.home.view

import com.depromeet.watni.R
import com.depromeet.watni.base.BaseFragment
import com.depromeet.watni.databinding.FragmentHistoryBinding

/*
 * Created by yunji on 2020-02-22
 */
class HistoryFragment private constructor() : BaseFragment<FragmentHistoryBinding>(R.layout.fragment_history) {

    companion object {

        fun getInstance() = HistoryFragment()
    }
}