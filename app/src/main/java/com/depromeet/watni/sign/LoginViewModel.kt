package com.depromeet.watni.sign

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by yunji on 2020-01-28
 */
class LoginViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    private val _loginStatus = MutableLiveData<Boolean>()
    private val _loginAvailable = MutableLiveData<Boolean>()
    private val _msgText = MutableLiveData<String>()
    val emailText = MutableLiveData<String>()
    val pwdText = MutableLiveData<String>()

    val loginStatus: LiveData<Boolean>
        get() = _loginStatus

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val loginAvailable: LiveData<Boolean>
        get() = _loginAvailable

    val msgText: LiveData<String>
        get() = _msgText

    fun attemptLogin() {

    }

    fun updateLoginAvailable() {
        _loginAvailable.value = !emailText.value.isNullOrBlank() && !pwdText.value.isNullOrBlank()
    }
}