package com.depromeet.watni.group

import com.depromeet.watni.R
import com.depromeet.watni.util.ResourceUtil

/*
 * Created by yunji on 08/04/2020
 */
enum class GroupState(val stateCode: Int) {
    JOIN(0), CREATE_NAME(1), CREATE_CODE(2);

    fun getMessage() = ResourceUtil.getString(
        when (this) {
            JOIN -> R.string.group_input_code
            CREATE_NAME -> R.string.group_create_name
            CREATE_CODE -> R.string.group_create_code
        }
    )

    companion object {

        fun of(stateCode: Int): GroupState {
            for (value in values()) {
                if (value.stateCode == stateCode) {
                    return value
                }
            }

            return JOIN
        }
    }
}