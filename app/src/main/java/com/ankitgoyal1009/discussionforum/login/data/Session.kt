package com.ankitgoyal1009.discussionforum.login.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Session(@PrimaryKey val email:String, val token:String)