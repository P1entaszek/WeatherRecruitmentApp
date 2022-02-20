package com.prod.weatherrecruitmentapp.di

import com.prod.weatherrecruitmentapp.datasource.remotedatasource.WeatherApiService
import com.prod.weatherrecruitmentapp.Config.openWeatherMapApi
import com.prod.weatherrecruitmentapp.feature.weatherList.WeatherApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Piotr Jaszczurowski on 20.02.2022
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(openWeatherMapApi)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideWeatherApiService(retrofit: Retrofit): WeatherApiService =
        retrofit.create(WeatherApiService::class.java)

    @Provides
    fun providesWeatherApiRepository(weatherApiService: WeatherApiService) =
        WeatherApiRepository(weatherApiService)

}