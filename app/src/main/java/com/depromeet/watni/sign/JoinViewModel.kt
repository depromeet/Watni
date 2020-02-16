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
    private val _emailText = MutableLiveData<String>()
    private val _pwdText = MutableLiveData<String>()
    private val _pwdConfirmText = MutableLiveData<String>()
    private val _nameText = MutableLiveData<String>()
    private val _msgText = MutableLiveData<String>()

    val joinStatus: LiveData<Boolean>
        get() = _joinStatus

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val joinAvailable: LiveData<Boolean>
        get() = _joinAvailable

    val nameText: LiveData<String>
        get() = _nameText

    val emailText: LiveData<String>
        get() = _emailText

    val pwdText: LiveData<String>
        get() = _pwdText

    val pwdConfirmText: LiveData<String>
        get() = _pwdConfirmText

    val msgText: LiveData<String>
        get() = _msgText

    fun attemptJoin() {
        updateJoinAvailable()
        _isLoading.value = true
        val user = UserJoin(
            _emailText.value!!, _nameText.value!!, _pwdText.value!!, _pwdConfirmText.value!!
        )

        SignRepository.userJoin(user, success = {
            _msgText.value = ResourceUtil.getString(R.string.join_success)
            _joinStatus.value = true
            _isLoading.value = false
        }, failed = { _, _ ->
            _msgText.value = ResourceUtil.getString(R.string.msg_network_err)
            _isLoading.value = false
        })
    }

    fun updateJoinAvailable() {
        _joinAvailable.value = InputValidator.isJoinInfoValid(
            _nameText.value,
            _emailText.value,
            _pwdText.value,
            _pwdConfirmText.value
        )
    }
}