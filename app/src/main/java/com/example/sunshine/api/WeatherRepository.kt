package com.example.sunshine.api

import com.example.sunshine.model.WeatherPayload
import io.reactivex.Observable

interface WeatherRepository {
    suspend fun getWeatherPayload(): Observable<WeatherPayload>
}