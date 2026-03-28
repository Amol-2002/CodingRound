package com.example.apicalling1.api

import com.example.apicalling1.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
//https://dummyjson.com/user
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun getApiService(): ApiService {
        return getInstance().create(ApiService::class.java)

    }
}