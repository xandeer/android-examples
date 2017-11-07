package me.xandeer.examples.realm

import android.util.Log
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_realm.*
import me.xandeer.examples.BaseActivity
import me.xandeer.examples.R

class RealmActivity : BaseActivity() {
  override fun setContentView() {
    setContentView(R.layout.activity_realm)
  }

  override fun initData() {
    // Initialize Realm
    Realm.init(this)
    log("init realm")

    // Get a Realm instance for current thread: UI thread
    realm = Realm.getDefaultInstance()
    realm.addChangeListener {
      val results = realm.where(Person::class.java).findAll()
      show(results)
    }
  }

  override fun initView() {
    initPersonsView()
    initButtons()
  }

  private fun initPersonsView() {
    persons_view.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, persons)
    persons_view.setOnItemClickListener { parent, _, position, _ ->
      val name = parent.getItemAtPosition(position).toString()
          .removePrefix("name: ")
          .substringBefore(", age: ")
      val person = realm.where(Person::class.java)
          .equalTo("name", name)
          .findFirst()!!
      realm.executeTransaction {
        log("delete person: ${person.name}.")
        // remove a single object
        person.deleteFromRealm()
      }
    }
  }

  private fun initButtons() {
    initCreateButton()
    initAllButton()
    initFindByNameButton()
    initFindByAuthorButton()
  }

  private fun initCreateButton() {
    create.setOnClickListener {
      if (edit_name.text.isNotEmpty() && edit_age.text.isNotEmpty()) {
        val name = edit_name.text.toString()
        val age = edit_age.text.toString().toInt()
        val person = Person(name, age)

        realm.executeTransaction {
          // create or update
          it.copyToRealmOrUpdate(person)
          log("add person: ${person.name}, ${person.age}")
        }
        edit_age.setText("")
        edit_name.setText("")
      }
    }
  }

  private fun initAllButton() {
    all.setOnClickListener {
      val results = realm.where(Person::class.java).findAll()
      show(results)
    }
  }

  private fun initFindByNameButton() {
    find_by_name.setOnClickListener {
      if (edit_name.text.isNotEmpty()) {
        val results = realm.where(Person::class.java)
            .equalTo("name", edit_name.text.toString())
            .findAll()
        show(results)
      }
    }
  }

  private fun initFindByAuthorButton() {
    find_by_age.setOnClickListener {
      if (edit_age.text.isNotEmpty()) {
        val results = realm.where(Person::class.java)
            .equalTo("age", edit_age.text.toString().toInt())
            .findAll()
        show(results)
      }
    }
  }

  private fun show(results: RealmResults<Person>) {
    persons.clear()
    results.forEach {
      persons.add("name: ${it.name}, age: ${it.age}")
    }
    (persons_view.adapter as BaseAdapter).notifyDataSetChanged()
  }

  override fun onPause() {
    super.onPause()
    realm.executeTransaction {
      realm.deleteAll()
      log("delete all")
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    realm.removeAllChangeListeners()
    realm.close()
    log("close realm")
  }

  private fun log(msg: String) {
    Log.d(javaClass.simpleName, msg)
  }

  private lateinit var realm: Realm
  private var persons = arrayListOf<String>()
}
