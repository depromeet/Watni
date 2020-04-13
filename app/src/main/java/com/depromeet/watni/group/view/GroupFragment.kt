package com.depromeet.watni.group.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.R
import com.depromeet.watni.base.BaseFragment
import com.depromeet.watni.databinding.FragmentGroupBinding
import com.depromeet.watni.ext.hideMessage
import com.depromeet.watni.ext.showMessage
import com.depromeet.watni.group.GroupState
import com.depromeet.watni.group.GroupViewModel
import com.depromeet.watni.group.GroupViewModelFactory
import com.depromeet.watni.model.source.GroupRepository
import com.depromeet.watni.util.ResourceUtil
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
        private const val KEY_GROUP_NAME = "key_group_name"

        fun newInstance(groupState: GroupState, groupName: String?): GroupFragment {
            val args = Bundle().apply {
                putInt(KEY_GROUP_STATE, groupState.stateCode)
                putString(KEY_GROUP_NAME, groupName)
            }
            return GroupFragment().apply {
                arguments = args
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        groupActivity = activity as GroupActivity
        arguments?.let { groupState = GroupState.of(it.getInt(KEY_GROUP_STATE)) }
        binding = FragmentGroupBinding.bind(view)

        initBinding()
        initView()
        observeUiData()
    }

    private fun initView() {
        with(binding) {
            tvGroupMsg.text = groupState.getMessage()
            ibGroupBack.setOnClickListener { groupActivity.removeCurrentFragment() }
            btnOk.setOnClickListener { handleNextBtnEvent() }
        }
    }

    private fun handleNextBtnEvent() {
        when (groupState) {
            GroupState.JOIN -> viewModel.joinGroup()
            GroupState.CREATE_NAME -> groupActivity.replaceWithCodeFragment(viewModel.inputValue.value!!)
            GroupState.CREATE_CODE -> viewModel.createGroup(
                viewModel.inputValue.value!!,
                arguments?.getString(KEY_GROUP_NAME) ?: ResourceUtil.getString(R.string.unknown)
            )
            else -> {
                // do nothing
            }
        }
    }

    private fun observeUiData() {
        with(viewModel) {
            toastTextId.observe(viewLifecycleOwner, Observer {
                showToast(it)
            })
            msgTextId.observe(viewLifecycleOwner, Observer {
                binding.textInputLayout.showMessage(it)
            })
            inputValue.observe(viewLifecycleOwner, Observer {
                checkOkButtonEnable()
                binding.textInputLayout.hideMessage()
            })
            status.observe(viewLifecycleOwner, Observer { (activity as GroupActivity).finish() })
        }
    }
}