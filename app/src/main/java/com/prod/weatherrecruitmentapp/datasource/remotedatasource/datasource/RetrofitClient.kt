package com.prod.weatherrecruitmentapp.datasource.remotedatasource.datasource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Piotr Jaszczurowski on 20.02.2022
 */
object RetrofitClient {
    var openWeatherMapApi = "https://api.openweathermap.org/"

    val retrofitInstance: Retrofit
        get() {
           return Retrofit.Builder().baseUrl(openWeatherMapApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
}