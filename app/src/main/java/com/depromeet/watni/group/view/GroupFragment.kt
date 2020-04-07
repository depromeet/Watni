package com.depromeet.watni.group.view

import android.os.Bundle
import android.view.View
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseFragment
import com.depromeet.watni.databinding.FragmentGroupBinding
import com.depromeet.watni.group.GroupState

/*
 * Created by yunji on 2020-03-05
 */
class GroupFragment : BaseFragment<FragmentGroupBinding>(R.layout.fragment_group) {
    private lateinit var groupState: GroupState

    companion object {
        val TAG = GroupFragment::class.java.name
        private const val KEY_GROUP_STATE = "key_group_state"

        fun newInstance(groupState: GroupState): GroupFragment {
            val args = Bundle().apply {
                putInt(KEY_GROUP_STATE, groupState.stateCode)
            }
            return GroupFragment().apply {
                arguments = args
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            groupState = GroupState.of(it.getInt(KEY_GROUP_STATE))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        with(binding) {
            tvGroupMsg.text = groupState.getMessage()
            ibGroupBack.setOnClickListener { finish() }
        }
    }

    private fun finish() {
        (activity as GroupActivity).closeCurrentFragment()
    }
}