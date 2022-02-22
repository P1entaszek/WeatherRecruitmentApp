package com.prod.weatherrecruitmentapp.common

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Piotr Jaszczurowski on 21.02.2022
 */

fun getDaysListFromToday(size: Int): ArrayList<String> {
    val daysList = ArrayList<String>()
    for (i in 0..size) {
        daysList.add(getDatePlusDays(i))
    }
    return daysList
}

fun getDatePlusDays(days: Int): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, +days)
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    return formatter.format(calendar.time)
}
