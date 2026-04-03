package com.example.apiday2.navigation.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.apiday2.screen.HomeScreen
import com.example.apiday2.screen.MessageScreen
import com.example.apiday2.screen.NotificationScreen
import com.example.apiday2.screen.ProfileScreen

@Composable
fun BottomGraph ( navController : NavHostController,
                  parentNavController : NavHostController){


    NavHost(
        navController = navController,
        startDestination = BottomRoutes.Home
    ) {
        composable<BottomRoutes.Home> {
            HomeScreen(parentNavController)
        }
        composable<BottomRoutes.Message> {
            MessageScreen(parentNavController) // 🔥 change
        }
        composable<BottomRoutes.Notification> {
            NotificationScreen(parentNavController) // 🔥 change
        }
        composable<BottomRoutes.Profile> {
            ProfileScreen(parentNavController) // 🔥 change
        }
    }
}