package com.example.sunshine.utils

import com.example.sunshine.model.WeatherPayload
import com.google.gson.Gson
import org.junit.internal.Classes

object TestingMocks {

    fun getMockWeatherPayload(): WeatherPayload {
        return Gson().fromJson(
            NetworkMockUtil.convertStreamToString(
                "WeatherPayload.json",
                Classes.getClass(WeatherPayload::class.java.name).classLoader
            ),
            WeatherPayload::class.java
        )
    }
}