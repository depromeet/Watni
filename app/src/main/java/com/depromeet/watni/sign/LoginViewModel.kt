package com.depromeet.watni.sign

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.watni.model.request.UserLogin
import com.depromeet.watni.model.source.SignDataSource
import com.depromeet.watni.model.source.SignRepository

/**
 * Created by yunji on 2020-01-28
 */
class LoginViewModel : ViewModel() {
    private val signRepository: SignDataSource = SignRepository()

    private val _isLoading = MutableLiveData<Boolean>()
    private val _loginStatus = MutableLiveData<Boolean>()
    private val _loginAvailable = MutableLiveData<Boolean>()
    private val _msgText = MutableLiveData<String>()

    val emailText = MutableLiveData<String>()
    val pwdText = MutableLiveData<String>()

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val loginStatus: LiveData<Boolean>
        get() = _loginStatus

    val loginAvailable: LiveData<Boolean>
        get() = _loginAvailable

    val msgText: LiveData<String>
        get() = _msgText

    fun attemptLogin() {
        val user = UserLogin(emailText.value!!, pwdText.value!!)
        signRepository.userLogin(user, success = {
            _loginStatus.value = true
            _isLoading.value = false
        }, failed = { _, msg ->
            _msgText.value = msg
            _isLoading.value = false
        })
    }

    fun updateLoginAvailable() {
        _loginAvailable.value = !(emailText.value.isNullOrBlank() || pwdText.value.isNullOrBlank())
    }
}