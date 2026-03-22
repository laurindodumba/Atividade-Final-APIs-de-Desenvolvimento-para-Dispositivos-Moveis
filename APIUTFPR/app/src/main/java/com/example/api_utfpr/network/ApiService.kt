package com.example.api_utfpr.network

import com.example.api_utfpr.model.Car
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("content.json")
    fun getCars(): Call<List<Car>>
}