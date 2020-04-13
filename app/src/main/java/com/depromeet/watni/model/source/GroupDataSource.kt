package com.depromeet.watni.model.source

import com.depromeet.watni.model.request.CreateGroup
import com.depromeet.watni.model.request.JoinGroup
import com.depromeet.watni.model.request.NewGroupCode
import com.depromeet.watni.model.response.GroupCodeResponse
import com.depromeet.watni.model.response.JoinGroupResponse
import com.depromeet.watni.model.response.SearchGroupResponse

interface GroupDataSource {

    fun searchGroup(
        groupId: Int,
        success: (group: SearchGroupResponse) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun joinGroup(
        joinGroup: JoinGroup,
        success: (group: JoinGroupResponse) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun createGroup(
        createGroup: CreateGroup,
        success: (response: SearchGroupResponse) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun createGroupCode(
        groupId: Int,
        newGroupCode: NewGroupCode,
        success: (response: GroupCodeResponse) -> Unit,
        failed: (String, String?) -> Unit
    )
}