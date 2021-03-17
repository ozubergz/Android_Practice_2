package com.example.android_practice_2.repo

import com.example.android_practice_2.model.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("units") unit: String,
        @Query("appid") key: String
    ) : Response<Result>

}