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
class GroupViewModel : ViewModel() {
    private val groupRepository = GroupRepository()
    private val _status = MutableLiveData<Boolean>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _nextBtnAvailable = MutableLiveData<Boolean>()
    private val _okBtnAvailable = MutableLiveData<Boolean>()
    private val _msgText = MutableLiveData<String>()

    val invitationCode = MutableLiveData<String>("")
    val groupName = MutableLiveData<String>("")

    val status: LiveData<Boolean>
        get() = _status

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val nextBtnAvailable: LiveData<Boolean>
        get() = _nextBtnAvailable

    val okBtnAvailable: LiveData<Boolean>
        get() = _okBtnAvailable

    val msgText: LiveData<String>
        get() = _msgText

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