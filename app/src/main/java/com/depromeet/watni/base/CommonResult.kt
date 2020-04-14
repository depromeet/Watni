package com.depromeet.watni.base

/*
 * Created by yunji on 13/04/2020
 */
data class CommonResult<ITEM>(
    val status: CommonStatus,
    val item: ITEM?,
    val msg: String?
) {

    companion object {
        fun <T> success(data: T): CommonResult<T> =
            CommonResult(CommonStatus.SUCCESS, data, CommonStatus.SUCCESS.toString())

        fun <T> error(msg: String, data: T? = null): CommonResult<T> =
            CommonResult(CommonStatus.ERROR, data, msg)

        fun <T> loading(data: T? = null): CommonResult<T> =
            CommonResult(CommonStatus.LOADING, data, CommonStatus.LOADING.toString())
    }
}

enum class CommonStatus {
    SUCCESS, ERROR, LOADING
}