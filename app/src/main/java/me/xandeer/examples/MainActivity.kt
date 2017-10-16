package me.xandeer.examples

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import me.xandeer.examples.animation.PropertyAnimationActivity
import me.xandeer.examples.slidingtab.SlidingTabActivity
import me.xandeer.examples.toolbar.ToolbarActivity

class MainActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initMenusContainer()
  }

  private fun initMenusContainer() {
    menus_container.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, menus.keys.toCollection(ArrayList()) as ArrayList)

    menus_container.setOnItemClickListener { parent, _, position, _ ->
      startAction(parent.getItemAtPosition(position) as String)
    }
  }

  private fun startAction(name: String) {
    val intent = Intent(this, menus[name])
    this.startActivity(intent)
  }

  companion object {
    val menus = mapOf<String, Class<*>>(
        "Toolbar" to ToolbarActivity::class.java,
        "Sliding Tab" to SlidingTabActivity::class.java,
        "Property Animation" to PropertyAnimationActivity::class.java
    )
  }
}