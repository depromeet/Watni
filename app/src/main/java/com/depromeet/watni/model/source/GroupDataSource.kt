package com.depromeet.watni.model.source

import com.depromeet.watni.model.request.ApplyGroup
import com.depromeet.watni.model.request.CreateGroup
import com.depromeet.watni.model.request.UserLogin
import com.depromeet.watni.model.response.GroupResponse
import com.depromeet.watni.model.response.SearchGroupResponse

interface GroupDataSource {

    fun searchGroup(
        groupId: Int,
        success: (group: GroupResponse) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun createGroup(
        createGroup: CreateGroup,
        success: (response: SearchGroupResponse) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun enterGroup(
        user: UserLogin,
        success: () -> Unit,
        failed: (String, String?) -> Unit
    )

    fun applyGroup(
        applyGroup: ApplyGroup,
        success: () -> Unit,
        failed: (String, String?) -> Unit
    )
}