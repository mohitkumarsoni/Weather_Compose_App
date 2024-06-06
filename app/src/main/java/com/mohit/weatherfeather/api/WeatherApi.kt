package com.mohit.weatherfeather.api

import com.mohit.weatherfeather.ui.models.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/v1/current.json")
    suspend fun getWeather(
        @Query("key") apiKey : String,
        @Query("q") cityName : String
    ) : Response<WeatherModel>

}