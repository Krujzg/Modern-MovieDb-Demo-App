package com.example.core.logging

import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SharedModule {

    @Provides
    fun provideFormatStrategy(): FormatStrategy =
        PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true)
            .methodCount(1)
            .methodOffset(5)
            .tag("") // To replace the default PRETTY_LOGGER tag with a dash (-).
            .build()

    @Provides
    fun provideTimberLogger(formatStrategy: FormatStrategy): IDebugLogger =
        TimberDebugLogger(formatStrategy)
}