package com.orcchg.yandexcontest.stocklist.data.model

import com.squareup.moshi.Json
import java.math.BigDecimal

data class QuoteEntity(
    @Json(name = "o") val openPrice: BigDecimal,
    @Json(name = "h") val maxPrice: BigDecimal,
    @Json(name = "l") val minPrice: BigDecimal,
    @Json(name = "c") val currentPrice: BigDecimal,
    @Json(name = "pc") val prevClosePrice: BigDecimal
)
