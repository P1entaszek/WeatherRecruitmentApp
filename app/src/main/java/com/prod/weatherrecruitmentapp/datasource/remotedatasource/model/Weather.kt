package com.prod.weatherrecruitmentapp.datasource.remotedatasource.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Piotr Jaszczurowski on 20.02.2022
 */
data class Weather(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("main") var main: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("icon") var icon: String? = null
)
