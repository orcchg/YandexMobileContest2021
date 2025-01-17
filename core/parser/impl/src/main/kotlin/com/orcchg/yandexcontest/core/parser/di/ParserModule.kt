package com.orcchg.yandexcontest.core.parser.di

import com.orcchg.yandexcontest.core.parser.BigDecimalAdapter
import com.orcchg.yandexcontest.core.parser.BigIntegerAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object ParserModule {

    @Provides
    @Reusable
    fun moshi(): Moshi =
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .add(BigDecimalAdapter)
            .add(BigIntegerAdapter)
            .build()
}
