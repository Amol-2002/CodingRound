package com.example.bottomnavigation.TeacherPage.api


import com.example.bottomnavigation.TeacherPage.model.UserResponse
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): UserResponse

}
