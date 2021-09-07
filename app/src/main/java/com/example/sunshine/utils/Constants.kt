package com.example.sunshine.utils

object Constants {

    // Integers
    const val TEN = 10
    const val ONE_THOUSAND = 1000

    // Location
    const val PERMISSIONS_REQUEST_CODE = 647

    // WeatherService.kt constants
    const val BASE_URL = "https://api.openweathermap.org/"
    const val PATH = "data/2.5/forecast/daily?"

    // Default WeatherService Parameters
    const val DEFAULT_CITY = "Atlanta"
    const val JSON = "json"
    const val IMPERIAL = "imperial"

    // WeatherService Query Values
    const val LAT = "lat"
    const val LONG = "lon"
    const val Q = "q"
    const val MODE = "mode"
    const val CNT = "cnt"
    const val UNITS = "units"
    const val API_KEY_QUERY_VALUE = "apikey"
}