package com.depromeet.watni.ext

import androidx.appcompat.app.AppCompatActivity
import com.depromeet.watni.ViewModelFactory
import com.depromeet.watni.group.GroupState
import com.depromeet.watni.model.source.GroupRepository
import com.depromeet.watni.model.source.SignRepository

/*
 * Created by yunji on 20/04/2020
 */
fun AppCompatActivity.getViewModelFactory(): ViewModelFactory =
    ViewModelFactory(this, SignRepository, GroupRepository)

fun AppCompatActivity.getViewModelFactory(groupState: GroupState): ViewModelFactory =
    ViewModelFactory(this, SignRepository, GroupRepository, groupState)