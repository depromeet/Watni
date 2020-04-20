package com.depromeet.watni.listener

/*
 * Created by yunji on 21/04/2020
 */
interface OnTimeRangeSelectedListener {

    fun onTimeSelected(startHour: Int, startMin: Int, endHour: Int, endMin: Int)
}