package com.example.sahajnandinfo.ui.wallpaper


import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sahajnandinfo.viewmodel.WallpaperViewModel

@Composable
fun WallpaperScreen(vm: WallpaperViewModel = viewModel()) {

    val list by vm.wallpapers.collectAsState()

    LaunchedEffect(Unit) {
        vm.fetch("Birds")
    }

    LazyColumn {
        items(list) {
            WallpaperItem(it)
        }
    }
}