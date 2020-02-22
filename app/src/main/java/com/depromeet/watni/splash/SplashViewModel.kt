package com.depromeet.watni.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.watni.model.source.SignRepository
import com.depromeet.watni.util.SharedPrefUtil

/*
 * Created by yunji on 2020-02-22
 */
class SplashViewModel : ViewModel() {
    private val _authStatus = MutableLiveData<Boolean>()
    private val signRepository = SignRepository()

    val authStatus: LiveData<Boolean>
        get() = _authStatus

    fun isFirstLaunch() = SharedPrefUtil.isFirstLaunch()

    fun isLoggedIn() = SharedPrefUtil.isLoggedIn()

    fun hasGroup() = SharedPrefUtil.getGroupId() != -1

    fun checkAuthStatus() {
        signRepository.checkAuthState(success = {
            _authStatus.value = true
        }, failed = { _, _ ->
            _authStatus.value = false
        })
    }
}