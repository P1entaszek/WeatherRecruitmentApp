package com.prod.weatherrecruitmentapp.datasource.remotedatasource.model

/**
 * Created by Piotr Jaszczurowski on 20.02.2022
 */
import com.google.gson.annotations.SerializedName


data class LocalNames(
    @SerializedName("en") var en: String? = null,
    @SerializedName("pl") var pl: String? = null
)