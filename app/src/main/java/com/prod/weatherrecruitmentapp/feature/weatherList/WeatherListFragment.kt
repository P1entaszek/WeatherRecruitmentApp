package com.prod.weatherrecruitmentapp.feature.weatherList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.prod.weatherrecruitmentapp.R
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.datasource.RetrofitClient
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.datasource.WeatherApiService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await
import retrofit2.awaitResponse

@AndroidEntryPoint
class WeatherListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitInstance = RetrofitClient.retrofitInstance.create(WeatherApiService::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val decodedCityData =
                retrofitInstance
                    .getCityData("Warsaw", "1", getString(R.string.api_key))
            val decodedCity = decodedCityData.body()?.get(0)
            if (decodedCity != null){
                val weatherData = retrofitInstance.
                getWeatherData(decodedCity.lat.toString(), decodedCity.lon.toString(), "hourly,minutely,alerts,current", "metric", getString(R.string.api_key))
                    ?.body()
                Log.d("Response", weatherData!!.dailyWeather.get(0).humidity.toString())
            }
        }


    }
}