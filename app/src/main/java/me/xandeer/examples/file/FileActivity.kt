package me.xandeer.examples.file

import android.util.Log
import kotlinx.android.synthetic.main.activity_file.*
import me.xandeer.examples.BaseActivity
import me.xandeer.examples.R
import java.io.File
import java.nio.charset.Charset

class FileActivity : BaseActivity() {

  override fun setContentView() {
    setContentView(R.layout.activity_file)
  }

  override fun initTitle() {
    setSupportActionBar(toolbar)

    toolbar.setNavigationOnClickListener {
      finish()
    }
  }

  override fun initData() {
    makeDir()

    // 必须在 makeDir 后调用
    createFile()

    // 必须在 createFile 后调用
    readFile()
    copyFile()

    listFileNames()
  }

  private fun makeDir() {
    folder = File(filesDir, folderName)

    if (folder.exists()) {
      log("Directory \"${folder.path}\" existed.")
    } else {
      folder.mkdir()
      log("Create directory \"${folder.path}\".")
    }
  }

  private fun createFile() {
    file = File(folder, fileName)

    if (file.exists()) {
      log("File \"${file.path}\" existed.")
    } else {
      try {
        file.createNewFile()
        file.appendText("Hello.\n")
        log("Create \"${file.path}\" with \"Hello.\"")
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }

  private fun readFile() {
    if (file.isFile) {
      try {
        log("Content from \"${file.path}\": ${file.readText(Charset.forName("UTF-8"))}")
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }

  private fun copyFile() {
    copyFile = File(folder, copyName)

    if (file.isFile && !copyFile.isFile) {
      file.copyTo(copyFile)
      log("Copy \"${file.path}\" to \"${copyFile.path}\"")
    }
  }

  private fun listFileNames() {
    log("Files includes in ${folder.name}: ")
    log("--------")
    folder.listFiles().forEach {
      log(it.name)
    }
    log("--------")
  }

  override fun initView() {
    text_view.text = outStr
  }

  override fun onDestroy() {
    super.onDestroy()
    deleteFolder()
  }

  private fun deleteFolder() {
    folder.deleteRecursively()
  }

  private fun log(msg: String) {
    Log.d(javaClass.simpleName, msg)
    outStr += msg
    outStr += "\n\n"
  }

  private lateinit var folder: File
  private lateinit var file: File
  private lateinit var copyFile: File
  private var outStr = ""

  companion object {
    private val folderName = "xandeer"
    private val fileName = "a.txt"
    private val copyName = "a_copy.txt"
  }
}