package com.depromeet.watni.model.source

import android.util.Log
import com.depromeet.watni.model.request.CreateGroup
import com.depromeet.watni.model.request.JoinGroup
import com.depromeet.watni.model.request.NewGroupCode
import com.depromeet.watni.model.response.GroupCodeResponse
import com.depromeet.watni.model.response.JoinGroupResponse
import com.depromeet.watni.model.response.SearchGroupResponse
import com.depromeet.watni.network.NETWORK_ERROR_MSG
import com.depromeet.watni.network.RetrofitBuilder
import com.depromeet.watni.network.ServiceApi
import com.depromeet.watni.network.retrofitCallback
import com.depromeet.watni.util.NetworkUtil

/*
 * Created by yunji on 2020-02-22
 */
object GroupRepository : GroupDataSource {
    private val tag = GroupRepository::class.java.simpleName
    private val service: ServiceApi = RetrofitBuilder.service

    override fun searchGroup(
        groupId: Int,
        success: (group: SearchGroupResponse) -> Unit,
        failed: (String, String?) -> Unit
    ) {
        service.searchGroup(groupId).enqueue(retrofitCallback { response, throwable ->
            response?.let {
                if (it.isSuccessful) {
                    success(it.body()!!)
                } else {
                    Log.e(tag, NetworkUtil.parseErrorDescription(it.errorBody()))
                    failed(tag, NETWORK_ERROR_MSG)
                }
            }
            throwable?.let {
                Log.e(tag, throwable.message, throwable)
                failed(tag, NETWORK_ERROR_MSG)
            }
        })
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
                } else {
                    val errorBody = NetworkUtil.parseErrorDescription(it.errorBody())
                    failed(tag, errorBody)
                }
            }
            throwable?.let {
                Log.e(tag, throwable.message, throwable)
                failed(tag, NETWORK_ERROR_MSG)
            }
        })
    }

    override fun createGroupCode(
        groupId: Int,
        newGroupCode: NewGroupCode,
        success: (response: GroupCodeResponse) -> Unit,
        failed: (String, String?) -> Unit
    ) {
        service.createGroupCode(groupId, newGroupCode).enqueue(retrofitCallback { response, throwable ->
            response?.let {
                if (it.isSuccessful) {
                    success(it.body()!!)
                } else {
                    val errorBody = NetworkUtil.parseErrorDescription(it.errorBody())
                    failed(tag, errorBody)
                }
            }
            throwable?.let {
                Log.e(tag, throwable.message, throwable)
                failed(tag, NETWORK_ERROR_MSG)
            }
        })
    }

    override fun joinGroup(
        joinGroup: JoinGroup,
        success: (group: JoinGroupResponse) -> Unit,
        failed: (String, String?) -> Unit
    ) {
        service.joinGroup(joinGroup).enqueue(retrofitCallback { response, throwable ->
            response?.let {
                if (it.isSuccessful) {
                    success(it.body()!!)
                } else {
                    val errorBody = NetworkUtil.parseErrorDescription(it.errorBody())
                    failed(tag, errorBody)
                }
            }
            throwable?.let {
                Log.e(tag, throwable.message, throwable)
                failed(tag, NETWORK_ERROR_MSG)
            }
        })
    }
}