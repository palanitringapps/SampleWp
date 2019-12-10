package com.samplewp.base

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco


class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //initialize the shared preferences
        Fresco.initialize(this)
    }
}