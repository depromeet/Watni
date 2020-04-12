package com.depromeet.watni.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.depromeet.watni.BR

/*
 * Created by yunji on 2020-02-22
 */
abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel>(
    @LayoutRes private val layoutResId: Int
) : Fragment(layoutResId) {
    protected lateinit var binding: B

    protected abstract val viewModel: VM

    protected fun initBinding() {
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
    }
}