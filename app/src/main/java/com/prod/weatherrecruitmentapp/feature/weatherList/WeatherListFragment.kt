package com.prod.weatherrecruitmentapp.feature.weatherList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.prod.weatherrecruitmentapp.R
import com.prod.weatherrecruitmentapp.feature.weatherList.listadapter.DailyWeatherListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_weather.*

@AndroidEntryPoint
class WeatherListFragment : Fragment() {
    private val weatherListViewModel: WeatherListViewModel by viewModels()
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_citysearch.setOnClickListener {
            linearLayoutManager = LinearLayoutManager(context)
            recyclerview_weatherlist.layoutManager = linearLayoutManager

            weatherListViewModel.setValidationData(editTextSearchingBar.text.toString().trim())
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
            val weatherData = weatherDataList.take(weatherDays)
            val adapter = DailyWeatherListAdapter(weatherData)
            recyclerview_weatherlist.adapter = adapter
            weatherListViewModel.calculateTemperatures(weatherData)
        })

        weatherListViewModel.getCalculatedTemperatures().observe(viewLifecycleOwner, {  calculatedTemperatures ->
            calcTempLayout.visibility = View.VISIBLE
            tvValueMinTemp.text = calculatedTemperatures.minTemperature
            tvValueMaxTemp.text = calculatedTemperatures.maxTemperature
            tvValueMeanTemp.text = calculatedTemperatures.meanTemperature
            tvValueModeTemp.text = calculatedTemperatures.modeTemperature
        })

    weatherListViewModel.getErrorMessage().observe(viewLifecycleOwner, { error ->
        run {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }

    })
    }

    companion object{
        const val weatherDays = 5
    }
}