package com.example.android_practice_2.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Daily>,
    val message: Int
)