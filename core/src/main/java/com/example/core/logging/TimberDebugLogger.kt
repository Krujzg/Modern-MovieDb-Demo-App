package com.example.core.logging

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import timber.log.Timber
import javax.inject.Inject

interface IDebugLogger {
    fun plant()
}

internal class TimberDebugLogger @Inject constructor(
    private val formatStrategy: FormatStrategy
) : IDebugLogger {
    override fun plant() {
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String {
                return "(${element.fileName}:${element.lineNumber})#${element.methodName}"
            }

            override fun log(
                priority: Int,
                tag: String?,
                message: String,
                t: Throwable?
            ) {
                Logger.log(priority, "-$tag", message, t)
            }
        })
    }

}