package com.iramarjunior.parrot.modules.authentication.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.iramarjunior.parrot.R
import com.iramarjunior.parrot.modules.post.view.activity.PostActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setupView()
        signUpConfirm()
    }

    private fun signUpConfirm() {

        buttonSignUpConfirm.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, PostActivity::class.java))
            finish()
        }
    }

    private fun setupView() {

        setSupportActionBar(toolbar_sign_up)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
