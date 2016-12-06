package iii_conventions

import java.util.Calendar.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return toMillis().compareTo(other.toMillis())
    }

    fun MyDate.compareTo(other: MyDate): Boolean {
        return this.compareTo(other) == 0
    }

    fun toMillis(): Long {
        val instance = getInstance()
        instance.set(YEAR, year)
        instance.set(MONTH, month - 1)
        instance.set(DAY_OF_MONTH, dayOfMonth)
        return instance.timeInMillis
    }
}


operator fun MyDate.rangeTo(other: MyDate): DateRange = todoTask27()

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate> {
    override fun contains(value: MyDate): Boolean {
        val origin = start.toMillis()
        val other = endInclusive.toMillis()
        return value.toMillis() >= origin && value.toMillis() <= other
    }
}
