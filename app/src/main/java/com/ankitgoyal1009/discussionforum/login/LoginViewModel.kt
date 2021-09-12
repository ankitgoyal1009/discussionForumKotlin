package com.ankitgoyal1009.discussionforum.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ankitgoyal1009.discussionforum.login.data.Session
import com.ankitgoyal1009.discussionforum.login.data.User
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

class LoginViewModel(private val mApplication: Application) : AndroidViewModel(mApplication) {
    private var mRepository: LoginRepository = LoginRepository.getInstance()

//    val isUserLoggedIn: Boolean = true

    /**
     * This is new user registration method.
     */
    fun registerUser(displayName: String, email: String, pwd: String) {
        val user = User(displayName, email, pwd)
        viewModelScope.launch { mRepository.registerUser(mApplication, user) }
    }

    fun getUser(email: String): LiveData<User> {
        return mRepository.getUser(mApplication, email)
    }

    fun createSession(email: String) {
        viewModelScope.launch {
            mRepository.createSession(mApplication, Session(email, UUID.randomUUID().toString()))
        }
    }

    fun getSession(email: String): LiveData<Session> {
        return mRepository.getSession(mApplication, email)
    }

    fun isUserLoggedIn(): Boolean {
        var isLoggedIn:Boolean = false
        viewModelScope.launch {
            async {
                isLoggedIn = mRepository.getSessionCount(mApplication) > 0
            }.await()
        }
        return isLoggedIn
    }
}
