package com.prod.weatherrecruitmentapp.common

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Piotr Jaszczurowski on 21.02.2022
 */

fun formatTimestampToTime(timestamp: Double): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    return formatter.format(timestamp)
}
