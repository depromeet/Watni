package com.depromeet.watni.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.depromeet.watni.R

/*
 * Created by yunji on 08/04/2020
 */
fun FragmentManager.addNewFragment(containerId: Int, fragment: Fragment, tag: String? = null) {
    beginTransaction()
        .setCustomAnimations(R.anim.start_from_left, R.anim.exit_to_left, R.anim.start_from_left, R.anim.exit_to_right)
        .add(containerId, fragment, tag)
        .addToBackStack(null)
        .commit()
}

fun FragmentManager.replaceFragment(containerId: Int, fragment: Fragment, tag: String? = null) {
    beginTransaction()
        .replace(containerId, fragment, tag)
        .commit()
}