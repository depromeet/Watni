package com.depromeet.watni.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.watni.model.request.CreateGroup
import com.depromeet.watni.model.source.GroupRepository
import com.depromeet.watni.network.ALREADY_USED_CODE_MSG
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

    private val _nextBtnAvailable = MutableLiveData<Boolean>()
    val nextBtnAvailable: LiveData<Boolean> get() = _nextBtnAvailable

    private val _okBtnAvailable = MutableLiveData<Boolean>()
    val okBtnAvailable: LiveData<Boolean> get() = _okBtnAvailable

    private val _msgText = MutableLiveData<String>()
    val msgText: LiveData<String> get() = _msgText

    val invitationCode = MutableLiveData<String>("")
    val groupName = MutableLiveData<String>("")

    fun getUserName(): String = SharedPrefUtil.getUserInfo()?.name ?: ""

    fun checkNextButtonEnable() {
        _nextBtnAvailable.value = !invitationCode.value.isNullOrBlank()
    }

    fun checkOkButtonEnable() {
        _okBtnAvailable.value = !groupName.value.isNullOrBlank()
    }

    fun createGroup() {
        _isLoading.value = true
        groupRepository.createGroup(CreateGroup(invitationCode.value!!, groupName.value!!), success = {
            _status.value = true
            _isLoading.value = false
        }, failed = { _, _ ->
            _isLoading.value = false
            _msgText.value = ALREADY_USED_CODE_MSG
        })
    }

    fun enterGroup() {

    }
}