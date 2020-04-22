package com.depromeet.watni.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.depromeet.watni.databinding.BottomSheetPickBinding
import com.depromeet.watni.ui.model.BottomSheetContent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/*
 * Created by yunji on 18/04/2020
 */
class PickBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetPickBinding
    val maxCount = 3
    val bottomSheetContents = arrayOfNulls<BottomSheetContent>(maxCount)
    var title: String = ""

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

    private fun BottomSheetItemButton.bindBasicContent(bottomSheetContent: BottomSheetContent?) {
        if (bottomSheetContent == null) {
            visibility = View.GONE
            return
        }

        btnText = bottomSheetContent.text
        setBtnClickListener() { bottomSheetContent.onClick(this@PickBottomSheetFragment) }
    }

    companion object {
        private val TAG = PickBottomSheetFragment::class.java.name

        fun getInstance(
            titleText: String = "",
            vararg contents: BottomSheetContent // 1~3개 버튼에 대응 가능하도록
        ): PickBottomSheetFragment {
            val pickBottomSheetFragment = PickBottomSheetFragment()
            require(pickBottomSheetFragment.maxCount >= contents.size) {
                "ButtonContents should not be greater than ${pickBottomSheetFragment.maxCount}."
            }

            return pickBottomSheetFragment.apply {
                title = titleText
                contents.forEachIndexed { i, content -> bottomSheetContents[i] = content }
            }
        }
    }
}