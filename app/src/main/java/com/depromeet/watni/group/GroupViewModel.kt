package com.depromeet.watni.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.watni.R
import com.depromeet.watni.model.request.CreateGroup
import com.depromeet.watni.model.request.JoinGroup
import com.depromeet.watni.model.request.NewGroupCode
import com.depromeet.watni.model.source.GroupRepository
import com.depromeet.watni.network.ALREADY_USED_CODE_DESC
import com.depromeet.watni.network.NOT_EXIST_CODE
import com.depromeet.watni.util.ResourceUtil
import com.depromeet.watni.util.SharedPrefUtil

/*
 * Created by yunji on 2020-02-22
 */
class GroupViewModel(
    private val groupState: GroupState,
    private val groupRepository: GroupRepository
) : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> get() = _status

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _okBtnAvailable = MutableLiveData<Boolean>()
    val okBtnAvailable: LiveData<Boolean> get() = _okBtnAvailable

    private val _msgTextId = MutableLiveData<Int>()
    val msgTextId: LiveData<Int> get() = _msgTextId

    private val _toastTextId = MutableLiveData<Int>()
    val toastTextId: LiveData<Int> get() = _toastTextId

    val inputValue = MutableLiveData("") // two-way data binding

    fun getUserName() = SharedPrefUtil.getUserInfo()?.name ?: ResourceUtil.getString(R.string.unknown_user)

    fun checkOkButtonEnable() {
        _okBtnAvailable.value = !inputValue.value.isNullOrBlank()
    }

    fun createGroup(groupCode: String, groupName: String) {
        _isLoading.value = true
        groupRepository.createGroup(
            CreateGroup(groupCode, groupName),
            success = {
                createGroupCode(it.groupId, groupCode)
                _toastTextId.value = R.string.group_create_success
            }, failed = { _, _ ->
                _isLoading.value = false
            })
    }

    private fun createGroupCode(groupId: Int, groupCode: String) {
        groupRepository.createGroupCode(groupId, NewGroupCode(groupCode),
            success = {
                _status.value = true
                _isLoading.value = false
            }, failed = { _, errDesc ->
                _isLoading.value = false
                _msgTextId.value = if (errDesc == ALREADY_USED_CODE_DESC) {
                    R.string.group_already_used_code
                } else {
                    R.string.msg_create_group_err
                }
            })
    }

    fun joinGroup() {
        groupRepository.joinGroup(
            JoinGroup(inputValue.value!!),
            success = {
                _status.value = true
                _isLoading.value = false
                _toastTextId.value = R.string.group_enter_success
            }, failed = { _, errDesc ->
                _isLoading.value = false
                _msgTextId.value = if (errDesc == NOT_EXIST_CODE) {
                    R.string.group_code_not_exist
                } else {
                    R.string.msg_join_group_err
                }
            })
    }
}