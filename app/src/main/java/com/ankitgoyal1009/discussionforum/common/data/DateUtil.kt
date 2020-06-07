package com.ankitgoyal1009.discussionforum.common.data

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object {
        /**
         * Returns true if the given millis is in today's range
         */
        fun isItToday(millis: Long): Boolean {
            val nextDayStart = getNextDayStartMillis()
            val todayStart = nextDayStart - android.text.format.DateUtils.DAY_IN_MILLIS

            return millis >= todayStart && millis <= nextDayStart
        }

        /**
         * Returns the start millis of next day.
         */
        fun getNextDayStartMillis(): Long {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val date = cal.get(Calendar.DATE)
            cal.clear()
            //Set next day beginning to the calendar instance.
            cal.set(year, month, date + 1)

            return cal.timeInMillis
        }

        /**
         * This will format given date into dd MMM yyyy format
         */
        fun dateToString(date: Date?): String {
            return getFormatDdMmYyyy().format(date)
        }

        private fun getFormathhmm(): DateFormat {
            FORMAT_HHMM.setTimeZone(TimeZone.getDefault())
            return FORMAT_HHMM
        }


        private fun getFormatDdMmYyyy(): DateFormat {
            FORMAT_DD_MM_YYYY.setTimeZone(TimeZone.getDefault())
            return FORMAT_DD_MM_YYYY
        }

        /**
         * Returns the start millis of the given day.
         */
        fun getStartOfDay(milies: Long): Long {
            val cal = Calendar.getInstance()
            cal.clear()
            cal.timeInMillis = milies
            cal.set(Calendar.HOUR_OF_DAY, 0)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            return cal.timeInMillis
        }

        private val FORMAT_DD_MM_YYYY = SimpleDateFormat("dd MMM yyyy")
        private val FORMAT_HHMM = SimpleDateFormat("HH:mm")
    }
}