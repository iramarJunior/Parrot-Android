package com.iramarjunior.parrot.modules.authentication.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User : RealmObject() {

    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var username: String = ""
    var email: String = ""
}