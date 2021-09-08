package com.example.sunshine.api

import com.example.sunshine.BuildConfig
import com.example.sunshine.model.WeatherPayload
import com.example.sunshine.utils.Constants.API_KEY_QUERY_VALUE
import com.example.sunshine.utils.Constants.BASE_URL
import com.example.sunshine.utils.Constants.CNT
import com.example.sunshine.utils.Constants.DEFAULT_CITY
import com.example.sunshine.utils.Constants.IMPERIAL
import com.example.sunshine.utils.Constants.JSON
import com.example.sunshine.utils.Constants.LAT
import com.example.sunshine.utils.Constants.LONG
import com.example.sunshine.utils.Constants.MODE
import com.example.sunshine.utils.Constants.PATH
import com.example.sunshine.utils.Constants.Q
import com.example.sunshine.utils.Constants.TEN
import com.example.sunshine.utils.Constants.UNITS
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET(PATH)
    fun getWeather(
        @Query(Q) q: String = DEFAULT_CITY,
        @Query(MODE) mode: String = JSON,
        @Query(CNT) sortBy: Int = TEN,
        @Query(UNITS) units: String = IMPERIAL,
        @Query(API_KEY_QUERY_VALUE) apiKey: String = BuildConfig.API_KEY
    ): Observable<WeatherPayload>

    @GET(PATH)
    fun getWeatherFromLocation(
        @Query(LAT) lat: Double,
        @Query(LONG) long: Double,
        @Query(MODE) mode: String = JSON,
        @Query(CNT) sortBy: Int = TEN,
        @Query(UNITS) units: String = IMPERIAL,
        @Query(API_KEY_QUERY_VALUE) apiKey: String = BuildConfig.API_KEY
    ): Observable<WeatherPayload>

    // Singleton
    companion object {
        val weatherService: WeatherService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(WeatherService::class.java)
        }
    }
}