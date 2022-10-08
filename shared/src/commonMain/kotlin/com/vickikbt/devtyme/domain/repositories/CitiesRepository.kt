package com.vickikbt.devtyme.domain.repositories

import com.vickikbt.devtyme.domain.models.City
import com.vickikbt.devtyme.domain.models.Weather
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {

    suspend fun fetchCities(): Flow<List<City>>

    suspend fun fetchWeather(lat: Double, long: Double): Flow<Weather?>
}
