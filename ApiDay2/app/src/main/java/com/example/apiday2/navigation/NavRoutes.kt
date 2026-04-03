package com.example.apiday2.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoutes {

    @Serializable
    object Splash : NavRoutes()

    @Serializable
    object Login : NavRoutes()

    @Serializable
    object Main : NavRoutes()


}