package com.xdroid.app.mathematics.utils.helpers

import com.xdroid.app.mathematics.cmodel.TimeConverter

object ListItems {

    fun getMultiplicationList(): List<String> {
        return List(100) { index -> "${index + 1}" }
    }

    fun getMultiplicationList(number: Int): List<String> {
        return List(101) { index -> "$number X $index = ${index * number}" }
    }

    fun getMenuList(): List<String> {
        return listOf(
            "Multiplication",
//            "Simple Interest",
//            "Time Converter"
        )
    }

    fun getTimeList(): List<TimeConverter> {
        return listOf(
            TimeConverter("1", "Seconds to Minutes"),
            TimeConverter("2", "Minutes to Hours"),
            TimeConverter("3", "Hours to Days"),
            TimeConverter("4", "Days to Weeks"),
            TimeConverter("5", "Weeks to Months"),
            TimeConverter("6", "Months to Years"),
        )
    }
    fun getTimeListAll(): List<TimeConverter> {
        return listOf(
            TimeConverter("1", "Nanosecond"),
            TimeConverter("2", "Microsecond"),
            TimeConverter("3", "Millisecond"),
            TimeConverter("4", "Second"),
            TimeConverter("5", "Minute"),
            TimeConverter("6", "Hour"),
            TimeConverter("7", "Day"),
            TimeConverter("8", "Week"),
            TimeConverter("9", "Month"),
            TimeConverter("10", "Year")
        )
    }

    fun getTimes(): List<String> {
        return listOf(
            "Nanosecond",
            "Microsecond",
            "Millisecond",
            "Second",
            "Minute",
            "Hour",
            "Day",
            "Week",
            "Month",
            "Year"
        )
    }
    fun getWeeks(): List<String> {
        return listOf(
            "Week",
            "Month",
            "Year"
        )
    }
}