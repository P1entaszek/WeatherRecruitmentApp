package com.prod.weatherrecruitmentapp.datasource.remotedatasource.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Piotr Jaszczurowski on 20.02.2022
 */
data class DailyWeather(

    @SerializedName("temp") var temp: Temp? = Temp(),
    @SerializedName("humidity") var humidity: Double? = null
)