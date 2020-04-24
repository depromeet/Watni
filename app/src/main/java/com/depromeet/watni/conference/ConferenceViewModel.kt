package com.depromeet.watni.conference

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.watni.base.CommonResult
import com.depromeet.watni.model.request.Conference
import com.depromeet.watni.model.request.CreateConference
import com.depromeet.watni.model.source.ConferenceRepository
import com.depromeet.watni.util.EditInputValidator
import com.depromeet.watni.util.SharedPrefUtil

/*
 * Created by yunji on 08/04/2020
 */
class ConferenceViewModel(
    private val conferenceRepository: ConferenceRepository
) : ViewModel() {

    private val _commonResult = MutableLiveData<CommonResult<Conference>>()
    val commonResult: LiveData<CommonResult<Conference>> get() = _commonResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isAvailable = MutableLiveData<Boolean>()
    val isAvailable: LiveData<Boolean> get() = _isAvailable

    private val _msgTextId = MutableLiveData<Int>()
    val msgTextId: LiveData<Int> get() = _msgTextId

    val titleText = MutableLiveData<String>()
    val placeText = MutableLiveData<String>()

    fun createConference(conference: CreateConference) {
        _isLoading.value = true

        conferenceRepository.createConference(
            SharedPrefUtil.getUserInfo()?.getGroupId() ?: -1,
            conference,
            success = {
                _isLoading.value = false
                _commonResult.value = CommonResult.success(it)
            }, failed = { _, errDesc ->
                _isLoading.value = false
                _commonResult.value = CommonResult.error(errDesc ?: "createConference error")
            })
    }

    fun updateAvailable() {
        _isAvailable.value = EditInputValidator.isValidConference(titleText.value, placeText.value)
    }
}