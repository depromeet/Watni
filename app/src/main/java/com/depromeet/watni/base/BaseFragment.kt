package com.depromeet.watni.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/*
 * Created by yunji on 2020-02-22
 */
abstract class BaseFragment<B : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int
) : Fragment(layoutResId) {
    protected lateinit var binding: B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }
}