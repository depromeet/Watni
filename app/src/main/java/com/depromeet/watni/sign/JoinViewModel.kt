package com.depromeet.watni.sign

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.watni.R
import com.depromeet.watni.model.request.UserJoin
import com.depromeet.watni.model.source.SignRepository
import com.depromeet.watni.util.InputValidator
import com.depromeet.watni.util.ResourceUtil

/**
 * Created by yunji on 2020-01-28
 */
class JoinViewModel : ViewModel() {
    private val _joinStatus = MutableLiveData<Boolean>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _joinAvailable = MutableLiveData<Boolean>()
    private val _msgText = MutableLiveData<String>()
    val emailText = MutableLiveData<String>()
    val pwdText = MutableLiveData<String>()
    val pwdConfirmText = MutableLiveData<String>()
    val nameText = MutableLiveData<String>()

    val joinStatus: LiveData<Boolean>
        get() = _joinStatus

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val joinAvailable: LiveData<Boolean>
        get() = _joinAvailable

    val msgText: LiveData<String>
        get() = _msgText

    fun attemptJoin() {
        updateJoinAvailable()
        _isLoading.value = true
        val user = UserJoin(
            emailText.value!!, nameText.value!!, pwdText.value!!, pwdConfirmText.value!!
        )

        SignRepository.userJoin(user, success = {
            _msgText.value = ResourceUtil.getString(R.string.join_success)
            _joinStatus.value = true
            _isLoading.value = false
        }, failed = { _, msg ->
            _msgText.value = msg
            _isLoading.value = false
        })
    }

    fun updateJoinAvailable() {
        _joinAvailable.value = InputValidator.isJoinInfoValid(
            nameText.value,
            emailText.value,
            pwdText.value,
            pwdConfirmText.value
        )
    }
}