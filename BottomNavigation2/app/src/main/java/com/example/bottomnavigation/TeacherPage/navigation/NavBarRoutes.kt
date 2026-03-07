package com.example.bottomnavigation.TeacherPage.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavBarRoutes {

    @Serializable
    object Home : NavBarRoutes()

    @Serializable
    object Search : NavBarRoutes()

    @Serializable
   object Notification : NavBarRoutes()

    @Serializable
    object Profile : NavBarRoutes()

     @Serializable
    object LoginRoute
}