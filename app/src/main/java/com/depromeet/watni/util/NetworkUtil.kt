package com.depromeet.watni.util

import com.depromeet.watni.model.response.ErrorResponse
import com.depromeet.watni.network.INVALID_TOKEN_DESC
import com.depromeet.watni.network.NO_ERROR_DESC
import com.depromeet.watni.network.RetrofitBuilder
import okhttp3.ResponseBody
import retrofit2.Converter


object NetworkUtil {

    fun parseErrorDescription(errorBody: ResponseBody?) = errorBody?.let {
        val converter: Converter<ResponseBody, ErrorResponse> = RetrofitBuilder.basicRetrofit.responseBodyConverter(
            ErrorResponse::class.java, arrayOfNulls<Annotation>(0)
        )
        converter.convert(errorBody)?.description
    } ?: NO_ERROR_DESC

    fun isInvalidTokenErr(errorResponse: ResponseBody?): Boolean =
        parseErrorDescription(errorResponse).startsWith(INVALID_TOKEN_DESC)
}