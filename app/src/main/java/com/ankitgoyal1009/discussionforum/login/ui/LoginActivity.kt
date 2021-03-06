package com.ankitgoyal1009.discussionforum.login.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ankitgoyal1009.discussionforum.R
import com.ankitgoyal1009.discussionforum.discussions.ui.DiscussionsActivity
import com.ankitgoyal1009.discussionforum.login.LoginViewModel
import com.ankitgoyal1009.discussionforum.login.RegisterActivity
import com.ankitgoyal1009.discussionforum.login.data.User

class LoginActivity : AppCompatActivity() {
    private lateinit var model: LoginViewModel
    private lateinit var etEmail: EditText
    private lateinit var etPwd: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etEmail = findViewById(R.id.et_email)
        etPwd = findViewById(R.id.et_password)
        model = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    fun doLogin(view: View) {
        val etEmailText = etEmail.text.trim().toString()
        val etPwdText = etPwd.text.trim().toString()
        var valid = true
        if (TextUtils.isEmpty(etEmailText)) {
            etEmail.setError(getString(R.string.error_required))
            valid = false
        }

        if (TextUtils.isEmpty(etPwdText)) {
            etPwd.setError(getString(R.string.error_required))
            valid = false
        }

        model.getUser(etEmailText).observe(this, object : Observer<User> {
            override fun onChanged(user: User?) {
                if (user == null) {
                    Toast.makeText(
                        this@LoginActivity,
                        R.string.error_authentication_failed,
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }

                if (etPwdText == user.pwd) {
                    model.createSession(user.email)
                    DiscussionsActivity.startActivity(this@LoginActivity)
                    Toast.makeText(
                        this@LoginActivity,
                        "Login Success",
                        Toast.LENGTH_SHORT
                    ).show()
                    this@LoginActivity.finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        R.string.error_authentication_failed,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

        })
    }

    fun startRegistrationActivity(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}