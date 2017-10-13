package me.xandeer.examples.slidingtab

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager, private var numOfTabs: Int): FragmentPagerAdapter(fm) {
  override fun getItem(position: Int): Fragment {
    return when (position) {
      0 -> TabFragment1()
      1 -> TabFragment2()
      2 -> TabFragment3()
      else -> TabFragment1()
    }
  }

  override fun getCount(): Int {
    return numOfTabs
  }
}