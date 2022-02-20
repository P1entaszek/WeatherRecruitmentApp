package com.prod.weatherrecruitmentapp.datasource.remotedatasource.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Piotr Jaszczurowski on 20.02.2022
 */

data class City(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("coord") var coord: Coord? = Coord(),
    @SerializedName("country") var country: String? = null,
    @SerializedName("population") var population: Int? = null,
    @SerializedName("timezone") var timezone: Int? = null
)

