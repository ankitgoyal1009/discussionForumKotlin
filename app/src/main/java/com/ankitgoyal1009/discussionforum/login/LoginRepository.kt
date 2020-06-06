package com.ankitgoyal1009.discussionforum.login

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.ankitgoyal1009.discussionforum.common.data.DiscussionDatabase
import com.ankitgoyal1009.discussionforum.login.data.Session
import com.ankitgoyal1009.discussionforum.login.data.User
import java.util.*

class LoginRepository {
    companion object {
        private lateinit var INSTANCE: LoginRepository

        fun getInstance(): LoginRepository {
            synchronized(LoginRepository::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = LoginRepository()
                }
            }
            return INSTANCE
        }
    }

    /**
     * This method return user object for a given email.
     */
    fun getUser(context: Context, email: String): LiveData<User> {
        val userDao = DiscussionDatabase.getInstance(context).getUserDao()
        return userDao.getUser(email)
    }

    /**
     * This method will create a new user in the system. It also checks if the same email is already
     * being used in the system.
     */
    fun registerUser(context: Context, user: User): LiveData<User> {
        val userLD = getUser(context, user.email)
        object : AsyncTask<Any, Any, Any>() {
            override fun doInBackground(objects: Array<Any>): Any? {
                val userDao = DiscussionDatabase.getInstance(context).getUserDao()
                userDao.insert(user)
                return null
            }
        }.execute()
        return userLD
    }


    /**
     * This method return user object for a given email.
     */
    fun getSession(context: Context, email: String): LiveData<Session> {
        val userDao = DiscussionDatabase.getInstance(context).getSessionDao()
        return userDao.getSession(email)
    }

    fun createSession(context: Context, session: Session): LiveData<Session> {
        val sessionLiveData = getSession(context, session.email)
        object : AsyncTask<Any, Any, Any>() {
            override fun doInBackground(objects: Array<Any>): Any? {
                val userDao = DiscussionDatabase.getInstance(context).getSessionDao()
                userDao.insert(session)
                return null
            }
        }.execute()
        return sessionLiveData
    }
}
