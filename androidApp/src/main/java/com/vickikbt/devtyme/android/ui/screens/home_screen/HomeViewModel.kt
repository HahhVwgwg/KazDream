package com.vickikbt.devtyme.android.ui.screens.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.devtyme.data.network.models.CurrentUserDto
import com.vickikbt.devtyme.domain.models.City
import com.vickikbt.devtyme.domain.models.Weather
import com.vickikbt.devtyme.domain.repositories.AuthRepository
import com.vickikbt.devtyme.domain.repositories.CitiesRepository
import com.vickikbt.devtyme.domain.repositories.DateTimeRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    private val authRepository: AuthRepository,
    private val dateTimeRepository: DateTimeRepository,
    private val citiesRepository: CitiesRepository
) : ViewModel() {

    private val _currentUser = MutableLiveData<CurrentUserDto?>()
    val currentUser: LiveData<CurrentUserDto?> get() = _currentUser

    private val _greetingMessage = MutableLiveData<String>()
    val greetingMessage: LiveData<String> get() = _greetingMessage

    private val _currentDate = MutableLiveData<String>()
    val currentDate: LiveData<String> get() = _currentDate

    private val _daysOfWeek = MutableLiveData<List<String>>()
    val daysOfWeek: LiveData<List<String>> get() = _daysOfWeek

    private val _citiesList = MutableLiveData<List<City>>()
    val citiesList: LiveData<List<City>> get() = _citiesList

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather> get() = _weather

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    var selectedCity by mutableStateOf("")
    var selectedDate by mutableStateOf(1)

    init {
        getCurrentDate()
    }

    fun getCurrentUserProfile() {
        viewModelScope.launch {
            try {
                val response = authRepository.getUserProfile()
                response.collectLatest {
                    _currentUser.value = it
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
    }

    fun getTimeOfDay() {
        viewModelScope.launch {
            try {
                dateTimeRepository.getTimeOfDay().collectLatest {
                    _greetingMessage.value = it
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
    }

    private fun getCurrentDate() {
        viewModelScope.launch {
            try {
                dateTimeRepository.getCurrentDate().collectLatest {
                    _currentDate.value = it
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
    }

    fun getDaysOfWeek() {
        viewModelScope.launch {
            try {
                dateTimeRepository.getDaysOfWeek().collectLatest { list ->
                    _daysOfWeek.value = list
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
    }

    fun getCities() {
        viewModelScope.launch {
            try {
                citiesRepository.fetchCities()
                    .collectLatest { cityList ->
                        _citiesList.value = cityList
                        if (cityList.isNotEmpty()) {
                            selectedCity = cityList.first().name
                            fetchWeather(cityList.first().lat, cityList.first().lon)
                        }
                    }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
    }


    private fun fetchWeather(lat: Double, long: Double) {
        viewModelScope.launch {
            try {
                citiesRepository.fetchWeather(lat = lat, long = long)
                    .collectLatest {
                        _weather.value = it
                    }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
    }

    fun onCitySelected(city: City) {
        fetchWeather(lat = city.lat, long = city.lon)
    }

    fun onDateChanged(selectedDate: Int?) {

    }
}
