package com.prod.weatherrecruitmentapp.datasource.remotedatasource

import com.prod.weatherrecruitmentapp.Config
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.model.DailyWeather
import javax.inject.Inject

/**
 * Created by Piotr Jaszczurowski on 20.02.2022
 */
class WeatherApiRepository @Inject constructor(private val apiService: WeatherApiService) {

    suspend fun getCityLatLng(city: String) = result {
        apiService.getCityData(city, limit, Config.openWeatherMapApiKey)
    }

    suspend fun getWeatherData(lat: Double, lng: Double) = result {
        apiService.getWeatherData(
            lat.toString(),
            lng.toString(),
            excludeParameters,
            metric,
            Config.openWeatherMapApiKey
        )
    }

    companion object {
        private const val limit = "1"
        private const val excludeParameters = "hourly,minutely,alerts,current"
        private const val metric = "metric"
    }
}
