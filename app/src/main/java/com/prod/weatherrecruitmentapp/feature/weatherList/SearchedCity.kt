package com.prod.weatherrecruitmentapp.feature.weatherList

import com.prod.weatherrecruitmentapp.R

/**
 * Created by Piotr Jaszczurowski on 20.02.2022
 */
class SearchedCity(private var searchedCity: String) {
    var isValid: Boolean = false
    var errorMessage: Int? = null
    var city: String

    init {
        errorMessage = getValidationMessage()
        isValid = errorMessage == null
        city = searchedCity
    }

    private fun getValidationMessage(): Int? {
        return if (searchedCity.isNullOrEmpty()) R.string.errorMessage
        else null
    }
}