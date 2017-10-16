package me.xandeer.examples.toolbar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_toolbar.*
import me.xandeer.examples.BaseActivity
import me.xandeer.examples.R

class ToolbarActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_toolbar)

    initToolBar()
  }

  private fun initToolBar() {
    setSupportActionBar(toolbar)

    // 生成 PopupMenu
    generateSheetMenus()

    // 处理导行按钮的点击事件
    toolbar.setNavigationOnClickListener {
      this.finish()
    }

    // 处理自定义按钮的点击事件
    toolbar_sheet_trigger.setOnClickListener {
      sheetMenus.show()
    }
  }


  private fun generateSheetMenus() {
    sheetMenus = PopupMenu(this, toolbar_sheet_trigger)

    for (i in 0..4) {
      sheetMenus.menu.add(0, Companion.SHEET_START + i, i, "sheet${i + 1}")
    }

    sheetMenus.setOnMenuItemClickListener {
      toolbar.title = it.title
      Toast.makeText(this, "Clicking Popup Menu: ${it.order}", Toast.LENGTH_SHORT).show()
      true
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    toolbar.inflateMenu(R.menu.mainmenu)
    toolbar.setOnMenuItemClickListener { onOptionsItemSelected(it) }
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.action_share -> {
        val format = toolbar.menu.findItem(R.id.action_format)
        format.isEnabled = !format.isEnabled
      }
    }
    Toast.makeText(this, "Trigger action ${item.title}", Toast.LENGTH_SHORT).show()

    return super.onOptionsItemSelected(item)
  }

  private lateinit var sheetMenus: PopupMenu

  companion object {
    private val SHEET_START = Menu.FIRST
  }
}