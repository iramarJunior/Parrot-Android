package com.iramarjunior.parrot.modules.authentication.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iramarjunior.parrot.R
import com.iramarjunior.parrot.modules.post.view.activity.PostActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin()
        buttonSignUp()
    }

    private fun buttonLogin() {

        buttonLogin.setOnClickListener {
            startActivity(Intent(this@LoginActivity, PostActivity::class.java))
            finish()
        }
    }

    private fun buttonSignUp() {

        buttonSignUp.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            finish()
        }
    }
}
