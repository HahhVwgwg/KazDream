package com.vickikbt.devtyme.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainDto(

    @SerialName("temp") var temp: Double? = null,

    @SerialName("feels_like") var feelsLike: Double? = null,

    @SerialName("pressure") var pressure: Int? = null,

    @SerialName("humidity") var humidity: Int? = null
)