package com.depromeet.watni.base

interface BaseView<P : BasePresenter> {
    val presenter: P

    fun showProgress()

    fun hideProgress()
}