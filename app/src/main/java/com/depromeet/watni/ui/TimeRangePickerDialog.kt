package com.depromeet.watni.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.depromeet.watni.R
import com.depromeet.watni.databinding.TimeRangePickerDialogBinding
import com.depromeet.watni.ext.getDisplayedMinutes
import com.depromeet.watni.ext.setTimeInterval
import com.depromeet.watni.ext.setUsable
import com.depromeet.watni.listener.OnTimeRangeSelectedListener
import com.depromeet.watni.util.DateTimeUtil
import com.depromeet.watni.util.ResourceUtil


/*
 * Created by yunji on 21/04/2020
 */
class TimeRangePickerDialog : DialogFragment() {
    private lateinit var binding: TimeRangePickerDialogBinding
    var onTimeRangeSelectedListener: OnTimeRangeSelectedListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = TimeRangePickerDialogBinding.inflate(inflater)
        setUpTab()
        initView()
        return binding.root
    }

    private fun initView() {
        with(binding) {
            btnOk.setUsable(false)
            tpStart.setTimeInterval()
            tpEnd.setTimeInterval()
            tpStart.setOnTimeChangedListener { _, hourOfDay, _ ->
                btnOk.setUsable(
                    DateTimeUtil.isCorrectSequence(
                        hourOfDay, tpStart.getDisplayedMinutes(), tpEnd.hour, tpEnd.getDisplayedMinutes()
                    )
                )
            }
            tpEnd.setOnTimeChangedListener { _, hourOfDay, _ ->
                btnOk.setUsable(
                    DateTimeUtil.isCorrectSequence(
                        tpStart.hour, tpStart.getDisplayedMinutes(), hourOfDay, tpEnd.getDisplayedMinutes()
                    )
                )
            }
            btnCancel.setOnClickListener { dismiss() }
            btnOk.setOnClickListener {
                onTimeRangeSelectedListener?.onTimeSelected(
                    tpStart.hour, tpStart.getDisplayedMinutes(), tpEnd.hour, tpEnd.getDisplayedMinutes()
                )
                dismiss()
            }
        }
    }

    private fun setUpTab() {
        val tabHost = binding.tabHost.apply { setup() }
        val startTimeTitle = ResourceUtil.getString(R.string.conference_start_time)
        val endTimeTitle = ResourceUtil.getString(R.string.conference_end_time)

        val startTimeTab = tabHost.newTabSpec(startTimeTitle).apply {
            setContent(R.id.tab_start_time)
            setIndicator(startTimeTitle)
        }
        val endTimeTab = tabHost.newTabSpec(endTimeTitle).apply {
            setContent(R.id.tab_end_time)
            setIndicator(endTimeTitle)
        }
        tabHost.apply {
            addTab(startTimeTab)
            addTab(endTimeTab)
        }
    }

    fun show(fragmentManager: FragmentManager) {
        super.show(fragmentManager, TAG)
    }

    companion object {
        private val TAG = TimeRangePickerDialog::class.java.name

        fun newInstance(
            timeRangeSelectedListener: OnTimeRangeSelectedListener? = null
        ): TimeRangePickerDialog {
            return TimeRangePickerDialog().apply {
                onTimeRangeSelectedListener = timeRangeSelectedListener
            }
        }
    }
}