package com.vickikbt.devtyme.data.network

import com.vickikbt.devtyme.data.network.models.AccessTokenDto
import com.vickikbt.devtyme.data.network.models.CurrentUserDto
import com.vickikbt.devtyme.data.network.models.WeatherDto
import com.vickikbt.devtyme.domain.models.Weather
import kotlinx.coroutines.flow.Flow

interface ApiService {

    suspend fun fetchUserToken(code: String): AccessTokenDto?

    suspend fun getCurrentUser(): CurrentUserDto?

    suspend fun fetchWeather(lat: Double, long: Double): WeatherDto?
}
