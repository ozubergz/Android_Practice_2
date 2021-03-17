package com.example.android_practice_2.repo

import com.example.android_practice_2.model.Result
import retrofit2.Response

object WeatherRepo {

    private val weatherService = RetrofitInstance.weatherService

    suspend fun getForecast(city: String, unit: String, key: String): Response<Result> {
        return weatherService.getForecast(city, unit, key)
    }
}