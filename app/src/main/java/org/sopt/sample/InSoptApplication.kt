package org.sopt.sample

import android.app.Application
import timber.log.Timber

class InSoptApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
