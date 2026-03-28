package com.example.apicalling1.api

import com.example.apicalling1.model.UserResponse
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getposts(): UserResponse
}