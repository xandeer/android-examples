package me.xandeer.examples.slidingtab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_sliding_tab.*
import me.xandeer.examples.BaseActivity
import me.xandeer.examples.R

class SlidingTabActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sliding_tab)
    Log.d(javaClass.simpleName, "Activity ${javaClass.simpleName} created.")

    initToolbar()
    initTab()
    initPager()
  }

  private fun initToolbar() {
    setSupportActionBar(toolbar)

    toolbar.setNavigationOnClickListener {
      this.finish()
    }
  }

  private fun initTab() {
    tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.tab_1)))
    tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.tab_2)))
    tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.tab_3)))

    tab_layout.addOnTabSelectedListener(OnTabSelectedListener())
  }

  inner class OnTabSelectedListener: TabLayout.OnTabSelectedListener {
    override fun onTabSelected(tab: TabLayout.Tab) {
      pager.currentItem = tab.position
    }

    override fun onTabReselected(tab: TabLayout.Tab) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {
    }
  }

  private fun initPager() {
    pager.adapter = PagerAdapter(supportFragmentManager, tab_layout.tabCount)
    pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    toolbar.inflateMenu(R.menu.mainmenu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.action_format) {
      container.visibility = when (container.visibility) {
        View.VISIBLE -> View.INVISIBLE
        else -> View.VISIBLE
      }
    }
    return super.onOptionsItemSelected(item)
  }
}
