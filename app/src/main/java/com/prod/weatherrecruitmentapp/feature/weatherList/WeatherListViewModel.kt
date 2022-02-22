package com.prod.weatherrecruitmentapp.feature.weatherList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prod.weatherrecruitmentapp.R
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.ResponseData
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.WeatherApiRepository
import com.prod.weatherrecruitmentapp.feature.weatherList.model.CalculatedTemperatures
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.model.DailyWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Piotr Jaszczurowski on 20.02.2022
 */
@HiltViewModel
class WeatherListViewModel @Inject constructor(private val weatherApiRepository: WeatherApiRepository) :
    ViewModel() {
    private val latLngPair = MutableLiveData<Pair<Double, Double>>()
    private val searchedCity = MutableLiveData<SearchedCity>()
    private val weatherDataList = MutableLiveData<ArrayList<DailyWeather>>()
    private val calculatedTemperatures = MutableLiveData<CalculatedTemperatures>()
    private val apiError = MutableLiveData<Int>()

    fun setValidationData(city: String) {
        searchedCity.value = SearchedCity(city)
    }

    fun getValidationData() = searchedCity

    fun setSearchedCityData(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = weatherApiRepository.getCityLatLng(city)
            response.collect { responseData ->
                run {
                    when (responseData) {
                        is ResponseData.Succes -> {
                            if (responseData.httpCode == 200) {
                                Log.d(TAG, "Kod zwrotki z serwera: $responseData.httpCode")
                                val firstCity = responseData.data?.first()
                                if (firstCity != null) {
                                    latLngPair.postValue(Pair(firstCity.lat!!, firstCity.lng!!))
                                }
                            } else {
                                apiError.postValue(R.string.something_went_wrong)
                            }
                        }
                        is ResponseData.Error -> {
                            apiError.postValue(R.string.cannot_find_the_city)
                            Log.d(TAG, responseData.errorMessage)
                        }
                    }
                }
            }
        }
    }

    fun getLatLng() = latLngPair

    fun searchWeatherByLatLng(latlng: Pair<Double, Double>?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (latlng != null) {
                val response = weatherApiRepository.getWeatherData(latlng.first, latlng.second)
                response.collect { responseData ->
                    run {

                        when (responseData) {
                            is ResponseData.Succes -> {
                                Log.d(TAG, "Kod zwrotki z serwera: $responseData.httpCode")
                                if (responseData.httpCode == 200) {
                                    weatherDataList.postValue(responseData.data!!.dailyWeather)
                                } else {
                                    apiError.postValue(R.string.something_went_wrong)
                                }
                            }
                            is ResponseData.Error -> {
                                apiError.postValue(R.string.cannot_find_weather_data)
                                Log.d(TAG, responseData.errorMessage)
                            }
                        }
                    }
                }
            }
        }
    }

    fun getWeatherDataList() = weatherDataList

    fun calculateTemperatures(weatherDataList: List<DailyWeather>) {
        val calculatedTemperatures =
            TemperatureCalculations(weatherDataList).getCalculatedTempetures()
        this.calculatedTemperatures.postValue(calculatedTemperatures)
    }

    fun getCalculatedTemperatures() = calculatedTemperatures

    fun getErrorMessage() = apiError

    companion object {
        const val TAG = "WeatherListViewModel"
    }
}