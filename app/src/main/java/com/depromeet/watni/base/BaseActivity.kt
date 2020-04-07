package com.depromeet.watni.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.depromeet.watni.util.addNewFragment

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes val layoutId: Int
) : AppCompatActivity() {
    protected lateinit var binding: B
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)
    }

    open fun startNewFragment(containerId: Int, fragment: Fragment, tag: String? = null) {
        supportFragmentManager.addNewFragment(containerId, fragment, tag)
    }

    open fun removeCurrentFragment() {
        supportFragmentManager.popBackStack()
    }

    open fun removeAllFragment() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}