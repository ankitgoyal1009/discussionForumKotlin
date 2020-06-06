package com.ankitgoyal1009.discussionforum.common.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ankitgoyal1009.discussionforum.login.data.Session
import com.ankitgoyal1009.discussionforum.login.data.SessionDao
import com.ankitgoyal1009.discussionforum.login.data.User
import com.ankitgoyal1009.discussionforum.login.data.UserDao

@Database(entities = [User::class, Session::class], version = 2)
abstract class DiscussionDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getSessionDao(): SessionDao


    companion object {

        private lateinit var INSTANCE: DiscussionDatabase
        public open fun getInstance(context: Context): DiscussionDatabase {
            synchronized(DiscussionDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DiscussionDatabase::class.java,
                        "discussion.db"
                    )
                        .fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }
    }
}