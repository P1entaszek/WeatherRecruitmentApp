package com.prod.weatherrecruitmentapp.datasource.remotedatasource.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Piotr Jaszczurowski on 20.02.2022
 */

data class Temp(
    @SerializedName("day") var day: Double? = null,
    @SerializedName("night") var night: Double? = null,
    @SerializedName("morn") var morn: Double? = null
)