package com.example.sunshine.api

import com.example.sunshine.model.WeatherPayload
import io.reactivex.Observable

interface WeatherRepository {
    suspend fun getWeatherPayload(lat: Double?, long: Double?): Observable<WeatherPayload>

    // Singleton
    // Wrapping WeatherRepository in an implementation.
    companion object {
        var weatherRepository: WeatherRepository? = null
        fun getInstance(): WeatherRepository {
            if (weatherRepository == null) {
                weatherRepository = WeatherRepositoryImpl()
            }
            return weatherRepository!!
        }
    }
}