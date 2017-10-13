package me.xandeer.examples

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

open class BaseActivity: AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    super.onCreate(savedInstanceState, persistentState)
    Log.d("BaseActivity", "Activity ${javaClass.simpleName} created.")
  }
}