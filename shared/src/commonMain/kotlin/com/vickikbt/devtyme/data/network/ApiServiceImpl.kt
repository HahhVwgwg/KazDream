package com.vickikbt.devtyme.data.network

import com.vickikbt.devtyme.data.network.models.AccessTokenDto
import com.vickikbt.devtyme.data.network.models.CurrentUserDto
import com.vickikbt.devtyme.data.network.models.WeatherDto
import com.vickikbt.devtyme.domain.utils.Constants
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class ApiServiceImpl constructor(private val httpClient: HttpClient) : ApiService {

    override suspend fun fetchUserToken(code: String): AccessTokenDto? {
        return try {
            httpClient.post<AccessTokenDto>(urlString = "https://wakatime.com/oauth/token") {
                body = FormDataContent(
                    Parameters.build {
                        append("client_id", Constants.CLIENT_ID)
                        append("client_secret", Constants.CLIENT_SECRET)
                        append("redirect_uri", Constants.REDIRECT_URL)
                        append("grant_type", "authorization_code")
                        append("code", code)
                    }
                )
            }
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
            null
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
            null
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

    override suspend fun fetchWeather(lat: Double, long: Double): WeatherDto? {
        return try {
            httpClient.get<WeatherDto>(urlString = "https://api.openweathermap.org/data/2.5/weather") {
                url {
                    parameters.append("appid", "4af76c79df8d11f6295bc8323877e2e5")
                    parameters.append("units", "metric")
                    parameters.append("lon", long.toString())
                    parameters.append("lat", lat.toString())
                }
            }
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
            null
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
            null
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

    override suspend fun getCurrentUser(): CurrentUserDto? {
        return try {
            httpClient.get<CurrentUserDto>(urlString = "https://wakatime.com/api/v1/users/current")
        } catch (e: ServerResponseException) {
            println("500 error: ${e.message}")
            null
        } catch (e: ClientRequestException) {
            println("400 error: ${e.message}")
            null
        } catch (e: RedirectResponseException) {
            println("300 error: ${e.message}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }
}
