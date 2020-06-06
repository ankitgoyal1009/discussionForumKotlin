package com.ankitgoyal1009.discussionforum.login.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(session: Session)

    @Query("Select * from session where email =:email")
    fun getSession(email: String) :LiveData<Session>

    @Query("DELETE from session where email=:email")
    fun deleteSession(email: String)
}