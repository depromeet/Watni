package com.depromeet.watni.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.depromeet.watni.home.view.ConferenceFragment
import com.depromeet.watni.home.view.HistoryFragment

/*
 * Created by yunji on 2020-02-22
 */
class HomeViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val pages = arrayOf(ConferenceFragment.getInstance(), HistoryFragment.getInstance())
    private val pageTitles = arrayOf("일정", "출석기록")

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence? = pageTitles[position]
}