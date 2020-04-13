package com.depromeet.watni.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.model.source.GroupRepository
import com.depromeet.watni.model.source.SignRepository

/*
 * Created by yunji on 13/04/2020
 */
@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val signRepository: SignRepository,
    private val groupRepository: GroupRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = HomeViewModel(signRepository, groupRepository) as T
}