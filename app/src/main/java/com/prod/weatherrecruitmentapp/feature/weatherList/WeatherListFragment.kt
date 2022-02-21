package com.prod.weatherrecruitmentapp.feature.weatherList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.prod.weatherrecruitmentapp.R
import com.prod.weatherrecruitmentapp.datasource.remotedatasource.WeatherApiService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_weather_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

        button_citysearch.setOnClickListener {
            weatherListViewModel.setValidationData(editTextSearchingBar.text.toString())
        }

        weatherListViewModel.getValidationData().observe(viewLifecycleOwner, { validatedData ->
            if (!validatedData.isValid) {
                Toast.makeText(context, getString(validatedData.errorMessage!!), Toast.LENGTH_SHORT)
                    .show()
            } else {
                weatherListViewModel.setSearchedCityData(validatedData.city)

            }
        })

        weatherListViewModel.getLatLng().observe(viewLifecycleOwner, { latlng ->
            weatherListViewModel.searchWeatherByLatLng(latlng)

        })

        weatherListViewModel.getWeatherDataList().observe(viewLifecycleOwner, { weatherDataList ->
            //TODO Zrobic adapter do wyswietlania listy i wrzucic dane ze zwrotki
            val list = weatherDataList.take(5)
            weatherDataList.get(0).clouds
        })
    }
}