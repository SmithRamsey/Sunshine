package com.example.sunshine.api

import com.example.sunshine.model.WeatherPayload
import io.reactivex.Observable

class WeatherRepositoryImpl : WeatherRepository {
    override suspend fun getWeatherPayload(): Observable<WeatherPayload> =
        WeatherService.getInstance().getWeather()
}