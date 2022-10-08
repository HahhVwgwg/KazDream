package com.vickikbt.devtyme.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WeatherDto(
    @SerialName("main") var mainDto: MainDto? = MainDto(),
)