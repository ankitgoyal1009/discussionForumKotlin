package com.ankitgoyal1009.discussionforum.login.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User constructor(
    val displayName: String,
    @PrimaryKey
    var email: String,
    var pwd: String
)