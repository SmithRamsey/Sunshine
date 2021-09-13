package com.example.sunshine.api

import com.example.sunshine.model.WeatherPayload
import io.reactivex.Observable

interface WeatherRepository {
    suspend fun getWeatherPayload(lat: Double?, long: Double?): Observable<WeatherPayload>

    // Singleton
    // Wrapping WeatherRepository in an implementation.
    companion object {
        val instance: WeatherRepository =
            WeatherRepositoryImpl()
    }
}