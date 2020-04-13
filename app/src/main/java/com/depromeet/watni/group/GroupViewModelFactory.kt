package com.depromeet.watni.group

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.depromeet.watni.model.source.GroupRepository

/*
 * Created by yunji on 08/04/2020
 */
@Suppress("UNCHECKED_CAST")
class GroupViewModelFactory(
    private val groupState: GroupState,
    private val groupRepository: GroupRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = GroupViewModel(groupState, groupRepository) as T
}