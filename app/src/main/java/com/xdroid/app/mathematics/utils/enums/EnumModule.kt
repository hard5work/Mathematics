package com.xdroid.app.mathematics.utils.enums


enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    IDLE
}


data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val title: String?
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(msg: String, title: String = "Error"): Resource<T> {
            return Resource(Status.ERROR, null, msg, title)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null, null)
        }

        fun <T> idle(): Resource<T> {
            return Resource(Status.IDLE, null, null, null)
        }
    }
}


enum class TimeFrame {
    Nanosecond,
    Microsecond,
    Millisecond,
    Second,
    Minute,
    Hour,
    Day,
    Week,
    Month,
    Year
}

enum class MiniTimeFrame {
    Week,
    Month,
    Year
}

enum class TimeFrames(val nanosecondsPerUnit: Long) {
    Nanosecond(1),
    Microsecond(1000),
    Millisecond(1000 * 1000),
    Second(1000 * 1000 * 1000),
    Minute(60 * 1000 * 1000 * 1000),
    Hour(60 * 60 * 1000 * 1000 * 1000),
    Day(24 * 60 * 60 * 1000 * 1000 * 1000),
    Week(7 * 24 * 60 * 60 * 1000 * 1000 * 1000),
    Month(30 * 24 * 60 * 60 * 1000 * 1000 * 1000),
    Year(365 * 24 * 60 * 60 * 1000 * 1000 * 1000)
}

fun stringToTimeFrame(input: String): TimeFrames {
    return when (input.lowercase()) {
        "nanosecond" -> TimeFrames.Nanosecond
        "microsecond" -> TimeFrames.Microsecond
        "millisecond" -> TimeFrames.Millisecond
        "second" -> TimeFrames.Second
        "minute" -> TimeFrames.Minute
        "hour" -> TimeFrames.Hour
        "day" -> TimeFrames.Day
        "week" -> TimeFrames.Week
        "month" -> TimeFrames.Month
        "year" -> TimeFrames.Year
        else -> TimeFrames.Nanosecond
    }
}

fun stringToTimeFrames(input: String): TimeFrame {
    return when (input.lowercase()) {
        "nanosecond" -> TimeFrame.Nanosecond
        "microsecond" -> TimeFrame.Microsecond
        "millisecond" -> TimeFrame.Millisecond
        "second" -> TimeFrame.Second
        "minute" -> TimeFrame.Minute
        "hour" -> TimeFrame.Hour
        "day" -> TimeFrame.Day
        "week" -> TimeFrame.Week
        "month" -> TimeFrame.Month
        "year" -> TimeFrame.Year
        else -> TimeFrame.Nanosecond
    }
}

fun stringToMiniTimeFrame(input: String): MiniTimeFrame {
    return when (input.lowercase()) {
        "week" -> MiniTimeFrame.Week
        "month" -> MiniTimeFrame.Month
        "year" -> MiniTimeFrame.Year
        else -> MiniTimeFrame.Week
    }
}