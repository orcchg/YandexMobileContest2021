package com.orcchg.yandexcontest.main.demo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.orcchg.yandexcontest.androidutil.viewBindings
import com.orcchg.yandexcontest.main.demo.R
import com.orcchg.yandexcontest.main.demo.databinding.MainDemoActivityBinding
import com.orcchg.yandexcontest.main.demo.di.DaggerMainDemoActivityComponent
import com.orcchg.yandexcontest.main.demo.ui.view.SectionsPagerAdapter
import javax.inject.Inject

internal class MainDemoActivity : AppCompatActivity(R.layout.main_demo_activity) {

    @Inject lateinit var sectionsPagerAdapter: SectionsPagerAdapter
    private val binding by viewBindings(MainDemoActivityBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainDemoActivityComponent.factory()
            .create(activity = this)
            .inject(this)
        super.onCreate(savedInstanceState)

        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.main_tab_stocks)
                1 -> getString(R.string.main_tab_favourite)
                else -> ""
            }
        }.attach()
    }
}
