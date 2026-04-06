package com.example.sahajnandinfo.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class NavRoutes {

    @Serializable
    object Splash : NavRoutes()

    @Serializable
    object Wallpaper : NavRoutes()

}