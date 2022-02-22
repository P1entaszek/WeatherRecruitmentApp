package com.prod.weatherrecruitmentapp.feature.weatherList

import com.prod.weatherrecruitmentapp.common.formatModesToString
import com.prod.weatherrecruitmentapp.common.getModes
import com.prod.weatherrecruitmentapp.common.roundValueToOneDecimal
import com.prod.weatherrecruitmentapp.feature.weatherList.model.CalculatedTemperatures
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.model.DailyWeather

/**
 * Created by Piotr Jaszczurowski on 21.02.2022
 */
class TemperatureCalculations(private val weatherDataList: List<DailyWeather>) {

    fun getCalculatedTempetures(): CalculatedTemperatures {
        val temperatureList: MutableList<Double> = ArrayList()
        weatherDataList.forEach { dailyWeather ->
            run {
                temperatureList.add(dailyWeather.temp!!.day!!)
                temperatureList.add(dailyWeather.temp!!.morn!!)
                temperatureList.add(dailyWeather.temp!!.night!!)
            }
        }

        val minTemperature = roundValueToOneDecimal(temperatureList.minOrNull())
        val maxTemperature = roundValueToOneDecimal(temperatureList.maxOrNull())
        val meanTemperature = roundValueToOneDecimal(temperatureList.average())
        val modeTemperature = getModes(temperatureList)?.let { formatModesToString(it) }

        val calculatedTemperatures = CalculatedTemperatures(
            minTemperature.toString(),
            maxTemperature.toString(),
            meanTemperature.toString(),
            modeTemperature.toString()
        )
        return calculatedTemperatures
    }
}