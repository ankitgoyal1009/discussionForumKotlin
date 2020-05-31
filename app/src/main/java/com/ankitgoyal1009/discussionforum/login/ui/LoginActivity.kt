package com.ankitgoyal1009.discussionforum.login.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ankitgoyal1009.discussionforum.R
import com.ankitgoyal1009.discussionforum.login.LoginViewModel
import com.ankitgoyal1009.discussionforum.login.RegisterActivity

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

    fun doLogin() {
        val etEmailText = etEmail.getText().toString()
        val etPwdText = etPwd.getText().toString()
        var valid = true
        if (TextUtils.isEmpty(etEmailText)) {
            etEmail.setError(getString(R.string.error_required))
            valid = false
        }

        if (TextUtils.isEmpty(etPwdText)) {
            etPwd.setError(getString(R.string.error_required))
            valid = false
        }

        //todo call view model to do login here
    }

    fun startRegistrationActivity(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}