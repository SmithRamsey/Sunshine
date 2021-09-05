package com.example.sunshine.api

import com.example.sunshine.model.WeatherPayload
import com.example.sunshine.utils.Constants.API_KEY
import com.example.sunshine.utils.Constants.API_KEY_QUERY_VALUE
import com.example.sunshine.utils.Constants.BASE_URL
import com.example.sunshine.utils.Constants.CNT
import com.example.sunshine.utils.Constants.DEFAULT_CITY
import com.example.sunshine.utils.Constants.FIVE
import com.example.sunshine.utils.Constants.IMPERIAL
import com.example.sunshine.utils.Constants.JSON
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
        @Query(API_KEY_QUERY_VALUE) apiKey: String = API_KEY
    ): Observable<WeatherPayload>

    companion object {
        var weatherService: WeatherService? = null
        fun getInstance(): WeatherService {
            if (weatherService == null) {
                val retrofit =
                    Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                weatherService = retrofit.create(WeatherService::class.java)
            }
            return weatherService as WeatherService
        }
    }
}