package com.prod.weatherrecruitmentapp.feature.weatherList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.model.DailyWeather
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.model.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    fun setValidationData(city: String) {
        searchedCity.value = SearchedCity(city)
    }

    fun getValidationData() = searchedCity

    fun setSearchedCityData(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = weatherApiRepository.getCityLatLng(city)
            if (response != null) {
                latLngPair.postValue(Pair(response.lat!!, response.lng!!))
            }
        }
    }

    fun getLatLng() = latLngPair

    fun searchWeatherByLatLng(latlng: Pair<Double, Double>?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (latlng != null) {
                val response = weatherApiRepository.getWeatherData(latlng.first, latlng.second)
                weatherDataList.postValue(response)
            }
        }
    }

    fun getWeatherDataList() = weatherDataList
}