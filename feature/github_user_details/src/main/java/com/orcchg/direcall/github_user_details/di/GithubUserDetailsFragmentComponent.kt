package com.orcchg.direcall.github_user_details.di

import com.orcchg.direcall.github_user_details.presentation.ui.GithubUserDetailsFragment
import com.orcchg.yandexcontest.network.api.NetworkApi
import com.orcchg.yandexcontest.scheduler.api.di.SchedulerApi
import dagger.Component

@Component(
    modules = [
        GithubUserDetailsModule::class
    ],
    dependencies = [
        NetworkApi::class,
        SchedulerApi::class
    ]
)
interface GithubUserDetailsFragmentComponent {

    @Component.Factory
    interface Factory {

        fun create(
            module: GithubUserDetailsModule,
            networkApi: NetworkApi,
            schedulerApi: SchedulerApi
        ): GithubUserDetailsFragmentComponent
    }

    fun inject(target: GithubUserDetailsFragment)
}
