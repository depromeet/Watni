package com.depromeet.watni.ext

import com.depromeet.watni.model.response.ErrorResponse
import com.depromeet.watni.network.INVALID_TOKEN_DESC
import com.depromeet.watni.network.NO_ERROR_DESC
import com.depromeet.watni.network.RetrofitBuilder
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response

/*
 * Created by yunji on 06/05/2020
 */
fun <T> Response<T>.hasUsableBody() = isSuccessful && body() != null

fun <T> Response<T>.isInvalidTokenErr() =
    !isSuccessful && errorBody().parseErrorDescription().startsWith(INVALID_TOKEN_DESC)

fun ResponseBody?.parseErrorDescription() = this?.let {
    val converter: Converter<ResponseBody, ErrorResponse> = RetrofitBuilder.retrofit.responseBodyConverter(
        ErrorResponse::class.java, arrayOfNulls<Annotation>(0)
    )
    converter.convert(this)?.description
} ?: NO_ERROR_DESC