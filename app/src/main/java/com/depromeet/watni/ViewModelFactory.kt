package com.depromeet.watni

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.depromeet.watni.conference.ConferenceViewModel
import com.depromeet.watni.group.GroupState
import com.depromeet.watni.group.GroupViewModel
import com.depromeet.watni.home.HomeViewModel
import com.depromeet.watni.model.source.ConferenceRepository
import com.depromeet.watni.model.source.GroupRepository
import com.depromeet.watni.model.source.SignRepository
import com.depromeet.watni.sign.JoinViewModel
import com.depromeet.watni.sign.LoginViewModel
import com.depromeet.watni.splash.SplashViewModel

/*
 * Created by yunji on 20/04/2020
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    owner: SavedStateRegistryOwner,
    private val signRepository: SignRepository,
    private val groupRepository: GroupRepository,
    private val conferenceRepository: ConferenceRepository,
    private val groupState: GroupState = GroupState.NONE,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(signRepository, groupRepository)
            isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel()
            isAssignableFrom(JoinViewModel::class.java) -> JoinViewModel()
            isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel()
            isAssignableFrom(GroupViewModel::class.java) -> GroupViewModel(groupState, groupRepository)
            isAssignableFrom(ConferenceViewModel::class.java) -> ConferenceViewModel(conferenceRepository)
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}