package com.depromeet.watni.home.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.watni.base.bindItems
import com.depromeet.watni.model.request.User

/*
 * Created by yunji on 14/04/2020
 */
@BindingAdapter("bindConferenceItems")
fun RecyclerView.bindConferenceItems(user: User?) {
    if (user != null && user.memberDetails.isNotEmpty()) {
        bindItems(user.memberDetails[0].group.conferences)
    }
}