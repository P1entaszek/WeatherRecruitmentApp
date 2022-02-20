package com.prod.weatherrecruitmentapp.datasource.remotedatasource.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Piotr Jaszczurowski on 20.02.2022
 */
data class DecodedCity(
	@SerializedName("name") var name: String? = null,
	@SerializedName("local_names") var localNames: LocalNames? = LocalNames(),
	@SerializedName("lat") var lat: Double? = null,
	@SerializedName("lon") var lng: Double? = null,
	@SerializedName("country") var country: String? = null,
	@SerializedName("state") var state: String? = null

)