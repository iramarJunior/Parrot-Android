package com.iramarjunior.parrot.modules.authentication.business

import android.util.Patterns
import com.iramarjunior.parrot.core.SessionController
import com.iramarjunior.parrot.core.network.BaseNetwork.Companion.HEADER_ACCESS_TOKEN
import com.iramarjunior.parrot.modules.authentication.database.AuthenticationDatabase
import com.iramarjunior.parrot.modules.authentication.model.SessionAuthentication
import com.iramarjunior.parrot.modules.authentication.network.AuthenticationNetwork

object AuthenticationBusiness {

    fun isEmailValid(email: String?): Boolean {
        return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String?): Boolean {
        return !password.isNullOrEmpty() && password.length == 8
    }

    fun isNameValid(name: String?): Boolean {
        return name != null && name.isNotEmpty() && name.length > 2
    }

    fun hasUserLogged(): Boolean {

        val sessionAuth = SessionController.sessionAuthentication

        return SessionController.user != null && sessionAuth != null && sessionAuth.isSessionAuthenticated()
    }

    fun registerUser(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ) {

        AuthenticationNetwork.requestRegisterUser(name, email, password,
            onSuccess = {
                onSuccess()
            },
            onError = {
                onError("Fail on create user!")
            })
    }

    fun doLogin(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ) {

        AuthenticationNetwork.requestLogin(email, password,
            onSuccess = { response ->

                val user = response.body()?.data

                val sessionAuth = SessionAuthentication().apply {
                    accessToken = response.headers()[HEADER_ACCESS_TOKEN] ?: ""
                }

                if (!sessionAuth.isSessionAuthenticated() && user != null) {
                    onError("Fail on authentication!")
                    return@requestLogin
                }

                AuthenticationDatabase.saveUser(user!!)
                AuthenticationDatabase.saveSessionAuthentication(sessionAuth)

                onSuccess()
            },
            onError = {
                onError("Login fail!")
            })
    }

    fun doLogout() {

        AuthenticationDatabase.clearAppData()
    }
}