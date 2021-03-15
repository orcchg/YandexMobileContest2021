package com.orcchg.yandexcontest.stocklist.api.model

import com.orcchg.yandexcontest.coremodel.Money

data class Stock(
    val id: String,
    val name: String,
    val price: Money,
    val priceDailyChange: Money,
    val logoUrl: String?
)
