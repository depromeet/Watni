package com.depromeet.watni.network

import com.depromeet.watni.R
import com.depromeet.watni.util.ResourceUtil

/*
 * Created by yunji on 2020-02-18
 */
const val RES_SUCCESS = 200
const val RES_FAIL = 400

// error notification
val NETWORK_ERROR_MSG = ResourceUtil.getString(R.string.msg_network_err)
val ALREADY_USED_EMAIL_MSG = ResourceUtil.getString(R.string.join_already_used_email)
val LOGIN_FAILED_MSG = ResourceUtil.getString(R.string.login_fail_msg)

// error description
const val GRANT_TYPE_PWD = "password"
const val GRANT_TYPE_TOKEN = "refresh_token"
const val NO_ERROR_DESC = "No error description"
const val ALREADY_USED_EMAIL_DESC = "Already exists Email"

// request header setting
const val HEADER_AUTH = "Authorization"
const val CONTENT_TYPE = "Content-Type"
const val ACCEPT_TYPE = "Accept"
const val TYPE_JSON = "application/json"
const val CLIENT_ID = "watni"
const val CLIENT_SECRET = "Nn5aDQTgw4Tn"
const val BASIC_TOKEN = "Basic"
const val BEARER_TOKEN = "Bearer"