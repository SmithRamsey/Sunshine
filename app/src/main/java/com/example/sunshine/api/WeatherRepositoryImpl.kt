package com.example.sunshine.api

import com.example.sunshine.model.WeatherPayload
import io.reactivex.Observable

class WeatherRepositoryImpl : WeatherRepository {
    override suspend fun getWeatherPayload(
        lat: Double?,
        long: Double?
    ): Observable<WeatherPayload> {
        return if (lat != null && long != null) {
            WeatherService.weatherService.getWeatherFromLocation(lat, long)
        } else {
            WeatherService.weatherService.getWeather()
        }
    }
}