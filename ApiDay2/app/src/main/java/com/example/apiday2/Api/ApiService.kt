package com.example.apiday2.Api

import com.example.apiday2.model.UserResponse
import retrofit2.http.GET

interface ApiService {
//https://jsonplaceholder.typicode.com/users
    @GET("users")
    suspend fun getUsers(): UserResponse
}