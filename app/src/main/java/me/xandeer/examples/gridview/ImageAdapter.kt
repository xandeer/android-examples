package me.xandeer.examples.gridview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import me.xandeer.examples.R

class ImageAdapter(val context: Context, private val count: Int): BaseAdapter() {
  // 单元格的 View
  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    val gridView: View

    if (convertView == null) {
      gridView = inflater.inflate(R.layout.adapter_grid_view, null)
      val imageView = gridView.findViewById<ImageView>(R.id.item)
      imageView.setImageResource(when (position) {
        0 -> R.drawable.grid_item0
        1 -> R.drawable.grid_item1
        2 -> R.drawable.grid_item2
        else -> R.drawable.grid_item0
      })
    } else {
      gridView = convertView
    }

    return gridView
  }

  // 要渲染的单元格数量
  override fun getCount(): Int {
    return count
  }

  // 在这个示例中不用，Android 要求实现此方法
  override fun getItem(position: Int): Any? {
    return null
  }

  // 在这个示例中不用，Android 要求实现此方法
  override fun getItemId(position: Int): Long {
    return 0
  }
}