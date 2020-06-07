package com.ankitgoyal1009.discussionforum.common.data

import androidx.room.TypeConverter
import java.util.*

class Convertors {
    @TypeConverter
    fun getDate(date: Long): Date {
        return Date(date)
    }

    @TypeConverter
    fun setDate(date: Date): Long {
        return date.time
    }
}