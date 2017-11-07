package me.xandeer.examples.gridview

import kotlinx.android.synthetic.main.activity_grid_view.*
import me.xandeer.examples.BaseActivity
import me.xandeer.examples.R

class GridViewActivity : BaseActivity() {

  override fun setContentView() {
    setContentView(R.layout.activity_grid_view)
  }

  override fun initView() {
    grid_view.adapter = ImageAdapter(this, 3)
    // 注册单元格的点击事件
    grid_view.setOnItemClickListener { _, _, position, _ ->
      toolbar.title = position.toString()
    }
  }
}
