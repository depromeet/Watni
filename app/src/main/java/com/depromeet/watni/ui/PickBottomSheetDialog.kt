package com.depromeet.watni.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.depromeet.watni.databinding.BottomSheetPickBinding
import com.depromeet.watni.ui.model.BottomSheetItemContent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/*
 * Created by yunji on 18/04/2020
 */
class PickBottomSheetDialog : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetPickBinding
    val maxCount = 3
    var title: String = ""
    val bottomSheetContents = arrayOfNulls<BottomSheetItemContent>(maxCount)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BottomSheetPickBinding.inflate(inflater)
        initView()
        return binding.root
    }

    private fun initView() {
        with(binding) {
            btnFirst.bindBasicContent(bottomSheetContents[0])
            btnSecond.bindBasicContent(bottomSheetContents[1])
            btnThird.bindBasicContent(bottomSheetContents[2])
            tvTitle.text = title
        }
    }

    fun show(fragmentActivity: FragmentActivity) {
        show(fragmentActivity.supportFragmentManager, TAG)
    }

    private fun BottomSheetItemButton.bindBasicContent(bottomSheetItemContent: BottomSheetItemContent?) {
        if (bottomSheetItemContent == null) {
            visibility = View.GONE
            return
        }

        btnText = bottomSheetItemContent.text
        setBtnClickListener() { bottomSheetItemContent.onClick(this@PickBottomSheetDialog) }
    }

    companion object {
        private val TAG = PickBottomSheetDialog::class.java.name

        fun getInstance(
            titleText: String = "",
            vararg itemContents: BottomSheetItemContent // 1~3개 버튼에 대응 가능하도록
        ): PickBottomSheetDialog {
            val pickBottomSheetFragment = PickBottomSheetDialog()
            require(pickBottomSheetFragment.maxCount >= itemContents.size) {
                "ButtonContents should not be greater than ${pickBottomSheetFragment.maxCount}."
            }

            return pickBottomSheetFragment.apply {
                title = titleText
                itemContents.forEachIndexed { i, content -> bottomSheetContents[i] = content }
            }
        }
    }
}