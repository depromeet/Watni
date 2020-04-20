package com.depromeet.watni.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelStoreOwner
import com.depromeet.watni.R
import com.depromeet.watni.ViewModelFactory
import com.depromeet.watni.group.GroupState
import com.depromeet.watni.model.source.GroupRepository
import com.depromeet.watni.model.source.SignRepository

/*
 * Created by yunji on 08/04/2020
 */
fun FragmentManager.addNewFragment(containerId: Int, fragment: Fragment, tag: String? = null) =
    commit {
        setCustomAnimations(
            R.anim.start_from_right,
            R.anim.exit_to_left,
            R.anim.start_from_left,
            R.anim.exit_to_right
        )
        add(containerId, fragment, tag)
        addToBackStack(null)
    }

fun FragmentManager.replaceFragment(containerId: Int, fragment: Fragment, tag: String? = null) =
    commit {
        setCustomAnimations(
            R.anim.start_from_right,
            R.anim.exit_to_left,
            R.anim.start_from_left,
            R.anim.exit_to_right
        )
        replace(containerId, fragment, tag)
        addToBackStack(null)
    }

fun Fragment.getViewModelStoreOwner(): ViewModelStoreOwner = try {
    requireActivity()
} catch (e: IllegalStateException) {
    this
}

fun Fragment.getViewModelFactory(): ViewModelFactory =
    ViewModelFactory(this, SignRepository, GroupRepository)

fun Fragment.getViewModelFactory(groupState: GroupState): ViewModelFactory =
    ViewModelFactory(this, SignRepository, GroupRepository, groupState)