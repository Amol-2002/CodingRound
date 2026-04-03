package com.example.apiday2.navigation.bottomnav

import kotlinx.serialization.Serializable
@Serializable

sealed class BottomRoutes {
    @Serializable
    object Home : BottomRoutes()

    @Serializable
    object Message : BottomRoutes()

    @Serializable
    object Notification : BottomRoutes()


    @Serializable
    object Profile : BottomRoutes()
}


