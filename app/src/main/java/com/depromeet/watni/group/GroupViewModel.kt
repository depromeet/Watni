package com.depromeet.watni.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.watni.util.SharedPrefUtil

/*
 * Created by yunji on 2020-02-22
 */
class GroupViewModel : ViewModel() {
    private val _joinStatus = MutableLiveData<Boolean>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _nextBtnAvailable = MutableLiveData<Boolean>()
    private val _msgText = MutableLiveData<String>()

    val inputText = MutableLiveData<String>()

    val joinStatus: LiveData<Boolean>
        get() = _joinStatus

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val nextBtnAvailable: LiveData<Boolean>
        get() = _nextBtnAvailable

    val msgText: LiveData<String>
        get() = _msgText

    fun getUserName(): String = SharedPrefUtil.getUserInfo()?.name ?: ""

    fun checkButtonEnable() {
        _nextBtnAvailable.value = !inputText.value.isNullOrBlank()
    }
}