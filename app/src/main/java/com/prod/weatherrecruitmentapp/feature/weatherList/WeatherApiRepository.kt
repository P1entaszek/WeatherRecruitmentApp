package com.prod.weatherrecruitmentapp.feature.weatherList

import com.prod.weatherrecruitmentapp.Config
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.WeatherApiService
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.model.DailyWeather
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.model.DecodedCity
import javax.inject.Inject

/**
 * Created by Piotr Jaszczurowski on 20.02.2022
 */
class WeatherApiRepository @Inject constructor(private val apiService: WeatherApiService) {
    private val limit = "1"
    private val excludeParameters = "hourly,minutely,alerts,current"
    private val metric = "metric"

    suspend fun getCityLatLng(city: String): DecodedCity? {
        val request = apiService.getCityData(city, limit, Config.openWeatherMapApiKey)
        return if (request.isSuccessful && request.errorBody() == null) {
            request.body()!!.firstOrNull()
        } else null
    }

    suspend fun getWeatherData(lat: Double, lng: Double): ArrayList<DailyWeather>? {
        val request = apiService.getWeatherData(
            lat.toString(),
            lng.toString(),
            excludeParameters,
            metric,
            Config.openWeatherMapApiKey
        )
        return if (request.isSuccessful && request.errorBody() == null) {
            request.body()!!.dailyWeather
        } else null
    }


}
