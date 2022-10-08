package com.vickikbt.devtyme.data.data_sources

import com.vickikbt.devtyme.data.mappers.toDomain
import com.vickikbt.devtyme.data.network.ApiService
import com.vickikbt.devtyme.domain.models.City
import com.vickikbt.devtyme.domain.models.Weather
import com.vickikbt.devtyme.domain.repositories.CitiesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CitiesRepositoryImpl(
    private val apiService: ApiService
) : CitiesRepository {

    override suspend fun fetchCities(): Flow<List<City>> {
        val cityList = arrayListOf(
            City(
                lon = 76.889709,
                lat = 43.238949,
                name = "Aлматы",
                image = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/Coat_of_arms_of_Almaty.svg/1200px-Coat_of_arms_of_Almaty.svg.png"
            ),
            City(
                lon = 71.449074,
                lat = 51.169392,
                name = "Астана",
                image = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/75/Emblem_of_Astana.svg/2048px-Emblem_of_Astana.svg.png"
            )
        )

        return flowOf(cityList)
    }

    override suspend fun fetchWeather(lat: Double, long: Double): Flow<Weather?> {
        val response = apiService.fetchWeather(lat = lat, long = long)
        return flowOf(response?.toDomain())
    }
}
