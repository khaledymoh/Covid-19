package io.covid19.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val items: MutableList<Fragment>
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment = items[position]

    override fun getItemCount(): Int = items.size
}