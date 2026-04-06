package com.example.sahajnandinfo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sahajnandinfo.data.model.Wallpaper
import com.example.sahajnandinfo.data.repository.WallpaperRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WallpaperViewModel : ViewModel() {

    private val repo = WallpaperRepository()

    private val _wallpapers = MutableStateFlow<List<Wallpaper>>(emptyList())
    val wallpapers: StateFlow<List<Wallpaper>> = _wallpapers

    fun fetch(category: String) {
        viewModelScope.launch {
            try {
                _wallpapers.value = repo.getWallpapers(category).data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}