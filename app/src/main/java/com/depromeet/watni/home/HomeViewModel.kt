package com.depromeet.watni.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.watni.base.CommonResult
import com.depromeet.watni.model.request.User
import com.depromeet.watni.model.source.GroupRepository
import com.depromeet.watni.model.source.SignRepository
import com.depromeet.watni.network.NETWORK_ERROR_MSG

/*
 * Created by yunji on 08/04/2020
 */
class HomeViewModel(
    private val signRepository: SignRepository,
    private val groupRepository: GroupRepository
) : ViewModel() {

    private val _userInfo = MutableLiveData<CommonResult<User>>()
    val userInfo: LiveData<CommonResult<User>> get() = _userInfo

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _msgTextId = MutableLiveData<Int>()
    val msgTextId: LiveData<Int> get() = _msgTextId

    private val _toastTextId = MutableLiveData<Int>()
    val toastTextId: LiveData<Int> get() = _toastTextId

    fun loadUserInfo() {
        _isLoading.value = true

        signRepository.getUserInfo(
            success = {
                _userInfo.value = CommonResult.success(it)
                _isLoading.value = false
            }, failed = { _, errDesc ->
                _userInfo.value = CommonResult.error(errDesc ?: NETWORK_ERROR_MSG)
                _isLoading.value = false
            })
    }
}