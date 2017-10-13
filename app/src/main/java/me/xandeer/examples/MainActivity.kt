package me.xandeer.examples

import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import me.xandeer.examples.slidingtab.SlidingTabActivity
import me.xandeer.examples.toolbar.ToolbarActivity

class MainActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initMenusContainer()
  }

  private fun initMenusContainer() {
    menus_container.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, menus)

    menus_container.setOnItemClickListener { _, _, position, _ ->
      when (position) {
        0 -> ToolbarActivity.actionStart(this)
        1 -> SlidingTabActivity.actionStart(this)
      }
    }
  }

  companion object {
    val menus = arrayListOf(ToolbarActivity::class.java.simpleName, SlidingTabActivity::class.java.simpleName)
  }
}