package com.example.sahajnandinfo.data.remote

import com.example.sahajnandinfo.data.model.WallpaperResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/android_category_wallpaper/")
    suspend fun getWallpapers(
        @Query("category") category: String
    ): WallpaperResponse
}