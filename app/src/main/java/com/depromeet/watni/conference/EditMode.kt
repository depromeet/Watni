package com.depromeet.watni.conference

/*
 * Created by yunji on 21/04/2020
 */
enum class EditMode(val code: Int) {
    NONE(-1), NEW(1), CHANGE(2);

    companion object {
        val TAG = EditMode::class.java.name
    }
}