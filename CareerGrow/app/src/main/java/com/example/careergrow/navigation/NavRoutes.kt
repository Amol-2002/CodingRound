package com.example.careergrow.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoutes {
    @Serializable
    object SplashScreen : NavRoutes()


    @Serializable
    object LoginScreen : NavRoutes()

    @Serializable
    object RegisterScreen : NavRoutes()


    @Serializable
    object MainScreen : NavRoutes()

    @Serializable
    object Progress : NavRoutes()

    @Serializable
    data class CProfileScreen(val mobile: String) : NavRoutes()


}