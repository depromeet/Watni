package com.depromeet.watni.model.source

import android.util.Log
import com.depromeet.watni.model.request.ApplyGroup
import com.depromeet.watni.model.request.CreateGroup
import com.depromeet.watni.model.request.UserLogin
import com.depromeet.watni.model.response.GroupResponse
import com.depromeet.watni.model.response.SearchGroupResponse
import com.depromeet.watni.network.*
import com.depromeet.watni.util.SharedPrefUtil

/*
 * Created by yunji on 2020-02-22
 */
class GroupRepository : GroupDataSource {
    private val tag = GroupRepository::class.java.simpleName
    private val service: ServiceApi = RetrofitBuilder.service

    override fun searchGroup(groupId: Int, success: (group: GroupResponse) -> Unit, failed: (String, String?) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createGroup(
        createGroup: CreateGroup,
        success: (response: SearchGroupResponse) -> Unit,
        failed: (String, String?) -> Unit
    ) {
        service.createGroup(createGroup).enqueue(retrofitCallback { response, throwable ->
            response?.let {
                if (it.isSuccessful) {
                    success(it.body()!!)
                    SharedPrefUtil.saveGroupId(it.body()!!.groupId)
                } else {
                    failed(tag, ALREADY_USED_CODE_MSG)
                }
            }
            throwable?.let {
                Log.e(tag, throwable.message, throwable)
                failed(tag, NETWORK_ERROR_MSG)
            }
        })
    }

    override fun enterGroup(user: UserLogin, success: () -> Unit, failed: (String, String?) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun applyGroup(applyGroup: ApplyGroup, success: () -> Unit, failed: (String, String?) -> Unit) {

    }
}