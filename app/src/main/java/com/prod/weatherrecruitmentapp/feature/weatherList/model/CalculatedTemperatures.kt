package com.prod.weatherrecruitmentapp.feature.weatherList.model

/**
 * Created by Piotr Jaszczurowski on 21.02.2022
 */
data class CalculatedTemperatures(
    val minTemperature: String,
    val maxTemperature: String,
    val meanTemperature: String,
    val modeTemperature: String
)
