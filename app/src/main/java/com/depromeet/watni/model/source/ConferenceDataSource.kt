package com.depromeet.watni.model.source

import com.depromeet.watni.model.request.Conference
import com.depromeet.watni.model.request.CreateConference

/*
 * Created by yunji on 24/04/2020
 */
interface ConferenceDataSource {

    fun createConference(
        groupId: Int,
        conference: CreateConference,
        success: (Conference) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun updateConference(
        groupId: Int,
        conferenceId: Int,
        conference: CreateConference,
        success: (Conference) -> Unit,
        failed: (String, String?) -> Unit
    )

    fun deleteConference(
        groupId: Int,
        conferenceId: Int,
        success: () -> Unit,
        failed: (String, String?) -> Unit
    )
}