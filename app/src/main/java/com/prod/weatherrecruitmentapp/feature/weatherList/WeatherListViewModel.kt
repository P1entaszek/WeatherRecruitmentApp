package com.prod.weatherrecruitmentapp.feature.weatherList

import android.provider.Settings.Global.getString
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prod.weatherrecruitmentapp.R
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.datasource.WeatherApiService
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.model.Coord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Piotr Jaszczurowski on 20.02.2022
 */
@HiltViewModel
class WeatherListViewModel @Inject constructor(private val apiService: WeatherApiService):
    ViewModel() {
    private val searchedCity = MutableLiveData<SearchedCity>()

    fun setSearchedCityData(city: String) {
        searchedCity.value = SearchedCity(city)
    }

    fun getSearchedCityData() = searchedCity

}