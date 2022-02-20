package com.prod.weatherrecruitmentapp.feature.weatherList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.prod.weatherrecruitmentapp.R
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.datasource.RetrofitClient
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.datasource.WeatherApiService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_weather_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await
import retrofit2.awaitResponse

@AndroidEntryPoint
class WeatherListFragment : Fragment() {
    private val weatherListViewModel: WeatherListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitInstance = RetrofitClient.retrofitInstance.create(WeatherApiService::class.java)

        button_citysearch.setOnClickListener {
            weatherListViewModel.setSearchedCityData(editTextSearchingBar.text.toString())
        }

        weatherListViewModel.getSearchedCityData().observe(viewLifecycleOwner, {
            if (!it.isValid){
                Toast.makeText(context, getString(it.errorMessage!!), Toast.LENGTH_SHORT).show()
            }

        })

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