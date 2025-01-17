package com.orcchg.yandexcontest.main.demo.ui.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.orcchg.yandexcontest.coremodel.StockSelection
import com.orcchg.yandexcontest.main.demo.ui.StockListDemoFragment
import javax.inject.Inject

internal class SectionsPagerAdapter @Inject constructor(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = StockSelection.values.size

    override fun createFragment(position: Int): Fragment =
        StockListDemoFragment.newInstance(StockSelection.values[position])
}
