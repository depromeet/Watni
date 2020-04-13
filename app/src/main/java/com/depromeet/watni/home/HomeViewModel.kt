package com.depromeet.watni.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.depromeet.watni.model.source.GroupRepository

/*
 * Created by yunji on 08/04/2020
 */
class HomeViewModel(
    groupRepository: GroupRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _msgTextId = MutableLiveData<Int>()
    val msgTextId: LiveData<Int> get() = _msgTextId

    private val _toastTextId = MutableLiveData<Int>()
    val toastTextId: LiveData<Int> get() = _toastTextId
}