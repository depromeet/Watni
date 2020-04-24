package com.depromeet.watni.model.source

import android.util.Log
import com.depromeet.watni.model.request.Conference
import com.depromeet.watni.model.request.CreateConference
import com.depromeet.watni.network.NETWORK_ERROR_MSG
import com.depromeet.watni.network.RetrofitBuilder
import com.depromeet.watni.network.ServiceApi
import com.depromeet.watni.network.retrofitCallback
import com.depromeet.watni.util.NetworkUtil

/*
 * Created by yunji on 24/04/2020
 */
object ConferenceRepository : ConferenceDataSource {
    private val tag = ConferenceRepository::class.java.simpleName
    private val service: ServiceApi = RetrofitBuilder.service

    override fun createConference(
        groupId: Int,
        conference: CreateConference,
        success: (Conference) -> Unit,
        failed: (String, String?) -> Unit
    ) {
        service.createConference(groupId, conference).enqueue(retrofitCallback { response, throwable ->
            response?.let {
                if (it.isSuccessful && it.body() != null) {
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

    override fun updateConference(
        groupId: Int,
        conferenceId: Int,
        conference: CreateConference,
        success: (Conference) -> Unit,
        failed: (String, String?) -> Unit
    ) {
        service.updateConference(groupId, conferenceId, conference).enqueue(retrofitCallback { response, throwable ->
            response?.let {
                if (it.isSuccessful && it.body() != null) {
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

    override fun deleteConference(groupId: Int, conferenceId: Int, success: () -> Unit, failed: (String, String?) -> Unit) {
        service.deleteConference(groupId, conferenceId).enqueue(retrofitCallback { response, throwable ->
            response?.let {
                if (it.isSuccessful && it.body() != null) {
                    run(success)
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
}