package com.iramarjunior.parrot.modules.authentication.network

import android.annotation.SuppressLint
import com.iramarjunior.parrot.core.network.BaseNetwork
import com.iramarjunior.parrot.modules.authentication.model.UserResponse
import com.iramarjunior.parrot.modules.authentication.model.UserWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AuthenticationNetwork : BaseNetwork() {

    private val API by lazy { getRetrofitBuilder().create(AuthenticationAPI::class.java) }

    fun requestLogin(
        email: String,
        password: String,
        onSuccess: (response: Response<UserResponse>) -> Unit,
        onError: () -> Unit
    ) {

        val userWrapper = UserWrapper(email, password)

        API.requestLogin(userWrapper).enqueue(object : Callback<UserResponse> {

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

                if (response.isSuccessful) {
                    onSuccess(response)
                } else {
                    onError()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                onError()
            }
        })
    }

    @SuppressLint("CheckResult")
    fun requestRegisterUser(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {

        val userWrapper = UserWrapper().apply {
            this.name = name
            this.email = email
            this.password = password
            this.passwordConfirmation = password
        }

//        doRequest(API, onSuccess, onError) { requestRegisterUser(userWrapper) }

        API.requestRegisterUser(userWrapper)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onSuccess()
                },
                {
                    onError()
                }
            )
    }
}