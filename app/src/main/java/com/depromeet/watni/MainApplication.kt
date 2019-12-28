package com.depromeet.watni

import android.app.Application
import android.content.Context

class MainApplication : Application() {

    companion object {
        private lateinit var APPLICATION_CONTEXT: Context

        @JvmStatic
        fun getContext(): Context {
            return APPLICATION_CONTEXT
        }
    }

    override fun onCreate() {
        super.onCreate()
        APPLICATION_CONTEXT = applicationContext
    }
}