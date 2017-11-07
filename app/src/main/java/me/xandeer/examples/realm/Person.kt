package me.xandeer.examples.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Person(
    @PrimaryKey var name: String = "",
    var age: Int = 16
) : RealmObject() {
}