package com.example.core.presentation

import com.example.core.domain.constants.Constants.FORMAT_STRATEGY_METHOD_COUNT
import com.example.core.domain.constants.Constants.FORMAT_STRATEGY_METHOD_OFFSET
import com.example.core.logging.IDebugLogger
import com.example.core.logging.TimberDebugLogger
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
            .methodCount(FORMAT_STRATEGY_METHOD_COUNT)
            .methodOffset(FORMAT_STRATEGY_METHOD_OFFSET)
            .tag("") // To replace the default PRETTY_LOGGER tag with a dash (-).
            .build()

    @Provides
    fun provideTimberLogger(formatStrategy: FormatStrategy): IDebugLogger =
        TimberDebugLogger(formatStrategy)
}