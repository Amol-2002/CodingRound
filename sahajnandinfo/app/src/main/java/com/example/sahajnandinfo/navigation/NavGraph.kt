package com.example.sahajnandinfo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sahajnandinfo.ui.splash.Splashscreen
import com.example.sahajnandinfo.ui.wallpaper.WallpaperScreen

@Composable

fun NavGraph(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash
    )
    {
        composable<NavRoutes.Splash> {
            Splashscreen(navController)
        }

        composable<NavRoutes.Wallpaper> {
            WallpaperScreen(navController)
        }


}}