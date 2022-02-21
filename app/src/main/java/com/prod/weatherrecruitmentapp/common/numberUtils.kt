package com.prod.weatherrecruitmentapp.common

import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.function.Function
import java.util.stream.Collectors

/**
 * Created by Piotr Jaszczurowski on 21.02.2022
 */
fun roundValueToOneDecimal(number: Double?): String? {
    return if (number == null) null
    else {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.FLOOR
        df.format(number).replace(",", ".")
    }
}

fun getModes(numbers: List<Double?>): List<Double?>? {
    val countFrequencies: Map<Double, Long> = numbers.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
    val maxFrequency = countFrequencies.values.stream()
        .mapToLong { count: Long? -> count!! }
        .max().orElse(-1)
    return countFrequencies.entries.stream()
        .filter { tuple: Map.Entry<Double, Long> -> tuple.value == maxFrequency }
        .map { it.key }
        .collect(Collectors.toList())
}

fun formatModesToString(doubleList: List<Double?>): String {
    var modes = StringBuilder()
    doubleList.forEach {
        modes.append(it.toString()).append(" ")
    }
    return modes.toString()
}

