package com.orcchg.yandexcontest.core.network.finnhub.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.request().newBuilder()
            .addHeader("X-Finnhub-Token", API_KEY)
            .build()
            .let(chain::proceed)
}

private const val REAL_API_KEY = "c1bs2j748v6sp0s54qp0"
private const val SANDBOX_API_KEY = "sandbox_c1bs2j748v6sp0s54qpg"
internal const val API_KEY = REAL_API_KEY
