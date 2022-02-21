package com.prod.weatherrecruitmentapp.common

import java.math.RoundingMode
import java.text.DecimalFormat

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

