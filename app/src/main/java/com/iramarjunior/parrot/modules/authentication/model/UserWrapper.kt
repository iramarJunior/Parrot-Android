package com.iramarjunior.parrot.modules.authentication.model

import com.google.gson.annotations.SerializedName

class UserWrapper(var email: String, var password: String) {

    constructor() : this("", "")

    var name: String = ""

    @SerializedName("password_confirmation")
    var passwordConfirmation: String = ""
}