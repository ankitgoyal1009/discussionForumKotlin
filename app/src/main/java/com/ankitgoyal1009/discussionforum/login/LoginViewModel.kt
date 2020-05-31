package com.ankitgoyal1009.discussionforum.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ankitgoyal1009.discussionforum.login.data.User

class LoginViewModel(private val mApplication: Application) : AndroidViewModel(mApplication) {
    private var mRepository: LoginRepository = LoginRepository.getInstance(mApplication)

    val isUserLoggedIn: Boolean = false//!TextUtils.isEmpty(mRepository.getSession(mApplication))

    /**
     * This is new user registration method.
     */
    fun registerUser(displayName: String, email: String, pwd: String): LiveData<User> {
        val user = User(displayName, email, pwd)
        return mRepository.registerUser(mApplication, user)
    }

    fun getUser(email: String): LiveData<User> {
        return mRepository.getUser(mApplication, email)
    }
}
