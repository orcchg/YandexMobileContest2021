package com.orcchg.yandexcontest.core.parser

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.math.BigDecimal

@Suppress("Unused")
internal object BigDecimalAdapter {

    @FromJson
    fun fromJson(string: String) = BigDecimal(string)

    @ToJson
    fun toJson(value: BigDecimal) = value.toString()
}
