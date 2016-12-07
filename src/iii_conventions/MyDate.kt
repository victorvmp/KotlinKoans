package iii_conventions

import java.util.*
import java.util.Calendar.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return toMillis().compareTo(other.toMillis())
    }

    fun MyDate.compareTo(other: MyDate): Boolean {
        return this.compareTo(other) == 0
    }

    fun toMillis(): Long {
        val c = Calendar.getInstance()
        c.set(year, month, dayOfMonth)
        return c.timeInMillis
    }

}

operator fun MyDate.plus(timeInterval: TimeInterval): MyDate {
    return plus(RepeatedTimeInterval(timeInterval, 1))
}

operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate {
    return addTimeIntervals(repeatedTimeInterval.interval, repeatedTimeInterval.times)
}

operator fun TimeInterval.times(n: Int) = RepeatedTimeInterval(this, n)

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

data class RepeatedTimeInterval(val interval: TimeInterval, val times: Int)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) :
        ClosedRange<MyDate>, Iterable<MyDate> {
    override fun contains(value: MyDate): Boolean {
        val origin = start.toMillis()
        val other = endInclusive.toMillis()
        return value.toMillis() >= origin && value.toMillis() <= other
    }

    override fun iterator(): Iterator<MyDate> {
        return RangeIterable(this)
    }
}

class RangeIterable(val range: DateRange) : Iterator<MyDate> {

    var current: MyDate = range.start

    override fun hasNext(): Boolean {
        return current <= range.endInclusive;
    }

    override fun next(): MyDate {
        if (hasNext()) {
            val result = current
            current = result.nextDay()
            return result
        }
        throw NoSuchElementException("No more elements")
    }
}