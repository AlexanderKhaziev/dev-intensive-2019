package ru.skillbranch.devintensive.extensions

//import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
        //else -> throw IllegalStateException("Invalid unit")
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(): String {
    val diff = Date().time - this.time
    var result: String
    when(diff) {
        in 0..SECOND -> result = "секунду назад"
        in SECOND..45 * SECOND -> result = "несколько секунд назад"
        in SECOND * 45..SECOND * 75 -> result = "минуту назад"
        in SECOND * 75..MINUTE * 45 -> {
            val minutes = floor((diff / MINUTE).toDouble()).toInt()
            if(minutes in 5..20) {
                result = "$minutes минут назад"
            } else if(minutes % 10 == 1) {
                result = "$minutes минуту назад"
            } else if(minutes % 10 in 2..4) {
                result = "$minutes минуты назад"
            } else {
                result = "$minutes минут назад"
            }
        }
        in MINUTE * 45..MINUTE * 75 -> result = "час назад"
        in MINUTE * 75..HOUR * 22 -> {
            val hours = floor((diff / HOUR).toDouble()).toInt()
            when(hours) {
                1, 21 -> result = "$hours час назад"
                in 2..4, 22 -> result = "$hours часа назад"
                else -> result = "$hours часов назад"
            }
        }
        in HOUR * 22..HOUR * 26 -> result = "день назад"
        in HOUR * 26..DAY * 360 -> {
            val days = floor((diff / DAY).toDouble()).toInt()
            if(days % 100 in 11..14) {
                result = "$days дней назад"
            } else if(days % 10 == 1) {
                result = "$days день назад"
            } else if(days % 10 in 2..4) {
                result = "$days дня назад"
            } else {
                result = "$days дней назад"
            }
        }
        else -> result = "более года назад"
    }

    return result
}

enum class TimeUnits {
    SECOND {
        override fun plural(n: Int): String {
            return when(n) {
                in 11..14 -> "$n секунд"
                else -> when(n % 10) {
                    1 -> "$n секунду"
                    in 2..4 -> "$n секунды"
                    else -> "$n секунд"
                }
            }
        }
    },
    MINUTE {
        override fun plural(n: Int): String {
            return when(n) {
                in 11..14 -> "$n минут"
                else -> when(n % 10) {
                    1 -> "$n минуту"
                    in 2..4 -> "$n минуты"
                    else -> "$n минут"
                }
            }
        }
    },
    HOUR {
        override fun plural(n: Int): String {
            return when(n) {
                1, 21 -> "$n час"
                in 2..4, in 22..24 -> "$n часа"
                else -> "$n часов"
            }
        }
    },
    DAY {
        override fun plural(n: Int): String {
            return when(n % 100) {
                in 11..14 -> "$n дней"
                else -> when(n %10) {
                    1 -> "$n день"
                    in 2..4 -> "$n дня"
                    else -> "$n дней"
                }
            }
//            return when(n) {
//                in 11..14 -> "$n секунд"
//                else -> when(n % 10) {
//                    1 -> "$n секунду"
//                    in 2..4 -> "$n секунды"
//                    else -> "$n секунд"
//                }
//            }
        }
    };

    abstract fun plural(n: Int): String
}