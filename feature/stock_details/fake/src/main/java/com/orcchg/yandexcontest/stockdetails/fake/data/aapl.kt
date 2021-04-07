package com.orcchg.yandexcontest.stockdetails.fake.data

import com.orcchg.yandexcontest.stockdetails.api.model.Candle

object AAPL {
    // on 9th Mar'21 14:09:59 - 15:09:59
    val m1 by lazy(LazyThreadSafetyMode.NONE) {
        val closePrices = listOf(119.12,119.08,119.14,119.19,119.06,119.1,118.9,118.87,118.93,118.95,119.01,118.99,118.88,118.88,118.72,118.67,118.84,118.7,119.11,119.04,118.99,119.29,118.93,118.86,119.34,119.36,119.34,119.4,119.4,119.45,119.38,119.16,118.96,118.89,118.93,119.06,119.15,119.3,119.36,119.43,119.29,119.24,119.32,119.7,119.88,119.94,119.93,119.99,120.38,120.27,120.45,120.57,120.63,120.67,120.73,120.65,120.74,120.57,120.52,120.54)
        val maxPrices = listOf(119.13,119.12,119.14,119.19,119.19,119.1,119.09,119.04,119,118.97,119.1,119.03,119,118.91,118.871,118.8,118.89,118.89,119.18,119.16,119.67,119.3,119.37,119.14,119.34,119.43,119.45,119.45,119.49,119.46,119.58,119.38,119.23,118.96,119.1,119.12,119.3,119.32,119.36,119.45,119.47,119.3,119.32,119.73,119.88,120.02,120.07,120.04,120.38,120.48,120.54,120.59,120.68,120.71,120.79,120.89,120.79,120.75,120.63,120.75)
        val minPrices = listOf(119.09,119.08,119.07,119.1,119.06,119.04,118.89,118.87,118.87,118.91,118.97,118.98,118.88,118.81,118.72,118.63,118.68,118.7,118.69,118.98,118.89,118.88,118.92,118.83,118.8,119.28,119.28,119.28,119.28,119.26,119.37,119.12,118.93,118.79,118.86,118.93,119.06,119.04,119.16,119.26,119.26,119.16,119.11,119.33,119.5,119.85,119.845,119.83,120,120.26,120.27,120.45,120.52,120.53,120.65,120.65,120.65,120.54,120.46,120.48)
        val openPrices = listOf(119.09,119.12,119.08,119.14,119.18,119.05,119.09,118.91,118.87,118.94,118.97,119.03,118.99,118.86,118.87,118.74,118.69,118.86,118.69,119.15,119.01,119.01,119.3,118.94,118.86,119.33,119.36,119.35,119.4,119.4,119.46,119.38,119.16,118.96,118.88,118.94,119.06,119.15,119.29,119.36,119.44,119.29,119.24,119.33,119.71,119.88,119.95,119.93,120,120.38,120.28,120.45,120.57,120.635,120.67,120.73,120.66,120.74,120.58,120.53)
        val ts = listOf(1615299000L,1615299060L,1615299120L,1615299180L,1615299240L,1615299300L,1615299360L,1615299420L,1615299480L,1615299540L,1615299600L,1615299660L,1615299720L,1615299780L,1615299840L,1615299900L,1615299960L,1615300020L,1615300080L,1615300140L,1615300200L,1615300260L,1615300320L,1615300380L,1615300440L,1615300500L,1615300560L,1615300620L,1615300680L,1615300740L,1615300800L,1615300860L,1615300920L,1615300980L,1615301040L,1615301100L,1615301160L,1615301220L,1615301280L,1615301340L,1615301400L,1615301460L,1615301520L,1615301580L,1615301640L,1615301700L,1615301760L,1615301820L,1615301880L,1615301940L,1615302000L,1615302060L,1615302120L,1615302180L,1615302240L,1615302300L,1615302360L,1615302420L,1615302480L,1615302540L)
        val volumes = listOf(30712L,17120L,26855L,32212L,24037L,18018L,34400L,35393L,27901L,11191L,26732L,13792L,23309L,22707L,34697L,79055L,66493L,36665L,105291L,68286L,3248072L,869830L,765363L,647722L,719322L,744238L,554226L,537558L,695511L,503922L,766286L,626864L,648107L,620953L,545353L,488765L,548069L,515333L,405355L,346953L,437665L,448079L,372857L,766008L,867356L,990366L,642107L,617759L,727788L,720268L,793926L,475252L,463881L,400210L,569088L,873471L,452051L,483063L,498590L,516948L)
        filler(openPrices, maxPrices, minPrices, closePrices, Candle.Resolution.m1, volumes, ts)
    }
}
