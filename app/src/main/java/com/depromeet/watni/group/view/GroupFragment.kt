package com.depromeet.watni.group.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseFragment
import com.depromeet.watni.databinding.FragmentGroupBinding
import com.depromeet.watni.group.GroupState
import com.depromeet.watni.group.GroupViewModel
import com.depromeet.watni.group.GroupViewModelFactory
import com.depromeet.watni.model.source.GroupRepository
import com.depromeet.watni.util.showToast

/*
 * Created by yunji on 2020-03-05
 */
class GroupFragment : BaseFragment<FragmentGroupBinding, GroupViewModel>(R.layout.fragment_group) {
    private lateinit var groupState: GroupState
    private lateinit var groupActivity: GroupActivity

    override val viewModel: GroupViewModel by lazy {
        ViewModelProvider(this, GroupViewModelFactory(groupState, GroupRepository))[GroupViewModel::class.java]
    }

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

        groupActivity = activity as GroupActivity
        arguments?.let {
            groupState = GroupState.of(it.getInt(KEY_GROUP_STATE))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeUiData()
    }

    private fun initView() {
        with(binding) {
            tvGroupMsg.text = groupState.getMessage()

            ibGroupBack.setOnClickListener { groupActivity.removeCurrentFragment() }
            btnOk.setOnClickListener { handleNextEvent() }
        }
    }

    private fun handleNextEvent() {
        when (groupState) {
            GroupState.JOIN -> viewModel.joinGroup()
            GroupState.CREATE_CODE -> {
                viewModel.createGroup("", "")
            }
            GroupState.CREATE_NAME -> groupActivity.startGroupFragment(GroupState.CREATE_CODE)
        }
    }

    private fun observeUiData() {
        with(viewModel) {
            msgTextId.observe(viewLifecycleOwner, Observer { showToast(it) })
            inputValue.observe(viewLifecycleOwner, Observer { checkOkButtonEnable() })
        }
    }
}