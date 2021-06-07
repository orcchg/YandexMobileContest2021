package com.orcchg.yandexcontest.core.schedulers.api

import io.reactivex.Scheduler

interface SchedulersFactory {

    fun io(): Scheduler

    fun main(): Scheduler

    fun useCase(): Scheduler
}
