package com.ankitgoyal1009.discussionforum.login

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ankitgoyal1009.discussionforum.R
import com.ankitgoyal1009.discussionforum.login.data.User


class RegisterActivity : AppCompatActivity() {
    private lateinit var mViewModel: LoginViewModel
    private lateinit var etEmail: EditText
    private lateinit var etDisplayName: EditText
    private lateinit var etPwd: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        etDisplayName = findViewById(R.id.et_display_name)
        etEmail = findViewById(R.id.et_email)
        etPwd = findViewById(R.id.et_password)
        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

    }


    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim { it <= ' ' }).matches()
    }

    fun doRegisterUser(view: View) {
        var valid = true
        val displayName = etDisplayName.text.toString().trim { it <= ' ' }
        val email = etEmail.text.toString().trim { it <= ' ' }
        val pwd = etPwd.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(displayName)) {
            etDisplayName.error = getString(R.string.error_required)
            valid = false
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.error = getString(R.string.error_required)
            valid = false
        }

        if (!isValidEmail(email)) {
            etEmail.error = getString(R.string.error_invalid_email)
            valid = false
        }

        if (TextUtils.isEmpty(pwd)) {
            etPwd.error = getString(R.string.error_required)
            valid = false
        }

        if (valid) {
            val userLiveData = mViewModel.getUser(email)
            val observer = object : Observer<User> {
                override fun onChanged(user: User?) {
                    if (user != null) {
                        Toast.makeText(
                            this@RegisterActivity,
                            R.string.error_user_already_exists,
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }
                    registerUser(displayName, email, pwd)
                    userLiveData.removeObservers(this@RegisterActivity)
                }

            }
            userLiveData.observe(this, observer)
        }
    }

    private fun registerUser(displayName: String, email: String, pwd: String) {
        val userLiveData = mViewModel.registerUser(displayName, email, pwd)
        userLiveData.observe(this,
            Observer { user ->
                if (user != null) {
                    Toast.makeText(
                        this@RegisterActivity,
                        R.string.registration_success,
                        Toast.LENGTH_SHORT
                    ).show()
                    this@RegisterActivity.finish()
                    userLiveData.removeObservers(this@RegisterActivity)
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "failed to register",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}