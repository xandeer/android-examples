package me.xandeer.examples.gridview

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_grid_view.*
import me.xandeer.examples.BaseActivity
import me.xandeer.examples.R

class GridViewActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_grid_view)

    initToolbar()
    initGridView()
  }


  private fun initToolbar() {
    setSupportActionBar(toolbar)

    toolbar.setNavigationOnClickListener {
      this.finish()
    }
  }

  private fun initGridView() {
    grid_view.adapter = ImageAdapter(this, 3)
    // 注册单元格的点击事件
    grid_view.setOnItemClickListener { parent, view, position, id ->
      toolbar.title = position.toString()
    }
  }
}
