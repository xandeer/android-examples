package me.xandeer.examples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log

abstract class BaseActivity: AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d("BaseActivity", "Activity: ${javaClass.simpleName} created.")

    setContentView()
    initData()
    initTitle()
    initView()
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d("BaseActivity", "Activity: ${javaClass.simpleName} destroyed.")
  }

  abstract fun setContentView()
  open fun initView() {}

  open fun initData() {}

  open fun initTitle() {
    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)

    if (toolbar.navigationIcon != null) {
      toolbar.setNavigationOnClickListener {
        this.finish()
      }
    }
  }
}