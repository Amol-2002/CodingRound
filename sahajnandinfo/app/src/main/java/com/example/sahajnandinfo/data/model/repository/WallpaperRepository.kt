package com.example.sahajnandinfo.data.repository

import com.example.wallpaperapp.data.remote.RetrofitInstance

class WallpaperRepository {
    suspend fun getWallpapers(category: String) =
        RetrofitInstance.api.getWallpapers(category)
}