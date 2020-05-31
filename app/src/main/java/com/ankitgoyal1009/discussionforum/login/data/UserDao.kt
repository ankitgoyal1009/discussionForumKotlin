package com.ankitgoyal1009.discussionforum.login.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("DELETE from User")
    fun deleteAll()

    @Query("SELECT * from user where email =:email")
    fun getUser(email: String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)
}