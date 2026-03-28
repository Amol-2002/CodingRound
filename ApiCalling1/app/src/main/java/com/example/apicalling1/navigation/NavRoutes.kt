package com.example.apicalling1.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoutes {

    @Serializable
    object splash : NavRoutes()

    @Serializable
    object login : NavRoutes()

    @Serializable
    object home : NavRoutes()

    @Serializable
    object topbar : NavRoutes()
}