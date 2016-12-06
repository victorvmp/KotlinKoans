package iii_conventions

import java.util.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return toMillis(this).compareTo(toMillis(other))
    }

    fun MyDate.compareTo(other: MyDate): Boolean {
        return this.compareTo(other) == 0
    }

    fun toMillis(date: MyDate): Long {
        val instance = Calendar.getInstance()
        instance.set(date.year, date.month, date.dayOfMonth)
        return instance.timeInMillis
    }
}


operator fun MyDate.rangeTo(other: MyDate): DateRange = todoTask27()

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate)
