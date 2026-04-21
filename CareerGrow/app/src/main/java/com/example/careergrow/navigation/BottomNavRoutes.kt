package com.example.careergrow.navigation

import kotlinx.serialization.Serializable
@Serializable
sealed class BottomNavRoutes {

@Serializable
    object HomeScreen : BottomNavRoutes()
@Serializable
    object MessageScreen : BottomNavRoutes()
@Serializable
    object NotificationScreen : BottomNavRoutes()
@Serializable
    object ProfileScreen : BottomNavRoutes()


}