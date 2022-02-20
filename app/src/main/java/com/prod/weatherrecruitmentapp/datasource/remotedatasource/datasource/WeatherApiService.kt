package com.prod.weatherrecruitmentapp.datasource.remotedatasource.datasource

import com.prod.weatherrecruitmentapp.datasource.remotedatasource.model.DecodedCity
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.model.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Piotr Jaszczurowski on 20.02.2022
 */
interface WeatherApiService {

    @GET("data/2.5/onecall")
    suspend fun getWeatherData(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("exclude") exclude: String?,
        @Query("units") units: String?,
        @Query("appid") appID: String?
    ): Response<WeatherData?>?

    @GET("geo/1.0/direct")
   suspend fun getCityData(
        @Query("q") cityName: String?,
        @Query("limit") limit: String?,
        @Query("appid") appID: String?
    ): Response<Array<DecodedCity>>
}