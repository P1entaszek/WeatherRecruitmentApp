package com.prod.weatherrecruitmentapp.di

import com.prod.weatherrecruitmentapp.datasource.remotedatasource.datasource.RetrofitClient
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.datasource.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Piotr Jaszczurowski on 20.02.2022
 */
@Module
@InstallIn(ActivityComponent::class)
object AppModule {
    private const val openWeatherMapApi = "https://api.openweathermap.org/"

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(openWeatherMapApi)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideWeatherApiService(retrofit: Retrofit): WeatherApiService =
        retrofit.create(WeatherApiService::class.java)

    
}