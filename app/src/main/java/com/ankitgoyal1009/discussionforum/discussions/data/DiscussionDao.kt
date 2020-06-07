package com.ankitgoyal1009.discussionforum.discussions.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.sql.Timestamp
@Dao
interface DiscussionDao {
    @Query("DELETE from Discussion")
    fun deleteAll()

    @Query("SELECT * from Discussion where id =:id")
    fun getDiscussion(id: String): LiveData<Discussion>

    @Query("SELECT * from Discussion ")
    fun getAllDiscussion(): LiveData<List<Discussion>>

    @Query("SELECT * from Discussion where date<=:timestamp ORDER BY date DESC")
    fun getAllPublishedDiscussion(timestamp: Long?): LiveData<List<Discussion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(discussion: Discussion)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(discussion: List<Discussion>)
}