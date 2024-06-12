package com.example.demomoviedbapp

import android.app.Application
import com.example.core.logging.IDebugLogger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MovieApplication : Application() {

    @Inject
    lateinit var debugLogger: IDebugLogger

    override fun onCreate() {
        super.onCreate()
        debugLogger.plant()
    }
}
