package com.prod.weatherrecruitmentapp.datasource.remotedatasource.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Piotr Jaszczurowski on 20.02.2022
 */
data class WeatherData(
    @SerializedName("lat"             ) var lat            : Double?          = null,
    @SerializedName("lon"             ) var lon            : Double?          = null,
    @SerializedName("timezone"        ) var timezone       : String?          = null,
    @SerializedName("timezone_offset" ) var timezoneOffset : Int?             = null,
    @SerializedName("daily"           ) var dailyWeather   : ArrayList<DailyWeather> = arrayListOf()
)
