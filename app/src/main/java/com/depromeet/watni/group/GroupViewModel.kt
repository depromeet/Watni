package com.depromeet.watni.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.watni.R
import com.depromeet.watni.model.request.CreateGroup
import com.depromeet.watni.model.source.GroupRepository
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

    val inputValue = MutableLiveData<String>("")

    fun getUserName() = SharedPrefUtil.getUserInfo()?.name ?: ResourceUtil.getString(R.string.unknown_user)

    fun checkOkButtonEnable() {
        _okBtnAvailable.value = !inputValue.value.isNullOrBlank()
    }

    fun createGroup(groupCode: String, groupName: String) {
        _isLoading.value = true
        groupRepository.createGroup(CreateGroup(groupCode, groupName), success = {
            _status.value = true
            _isLoading.value = false
        }, failed = { _, _ ->
            _isLoading.value = false
            _msgTextId.value = R.string.group_already_used_code
        })
    }

    fun joinGroup() {

    }
}