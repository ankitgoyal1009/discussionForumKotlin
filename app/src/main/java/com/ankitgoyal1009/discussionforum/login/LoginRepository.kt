package com.ankitgoyal1009.discussionforum.login

import android.content.Context
import androidx.lifecycle.LiveData
import com.ankitgoyal1009.discussionforum.common.data.DiscussionDatabase
import com.ankitgoyal1009.discussionforum.login.data.Session
import com.ankitgoyal1009.discussionforum.login.data.User

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
     * This method will create a new user in the system.
     */
    suspend fun registerUser(context: Context, user: User) {
        DiscussionDatabase.getInstance(context).getUserDao().insert(user)
    }

    /**
     * This method return user object for a given email.
     */
    fun getUser(context: Context, email: String): LiveData<User> {
        return DiscussionDatabase.getInstance(context).getUserDao().getUser(email)
    }


    /**
     * This method return user object for a given email.
     */
    fun getSession(context: Context, email: String): LiveData<Session> {
        return DiscussionDatabase.getInstance(context).getSessionDao().getSession(email)
    }

    suspend fun getSessionCount(context: Context): Int{
        return DiscussionDatabase.getInstance(context).getSessionDao().getSessionCount()
    }

    suspend fun createSession(context: Context, session: Session) {
        DiscussionDatabase.getInstance(context).getSessionDao().insert(session)
    }
}
