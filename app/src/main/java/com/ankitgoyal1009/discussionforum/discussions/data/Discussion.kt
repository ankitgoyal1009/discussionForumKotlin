package com.ankitgoyal1009.discussionforum.discussions.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Discussion(
    @PrimaryKey
    var id: String,
    var title: String,
    var description: String? = null,
    var date: Date? = null
)
