package com.example.apicalling1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apicalling1.screens.HomeScreen
import com.example.apicalling1.screens.Login
import com.example.apicalling1.screens.Splash
import com.example.apicalling1.screens.componants.TopAppBar

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavRoutes.splash
    )

    {
        composable<NavRoutes.splash> {
            Splash(navController)
        }
        composable<NavRoutes.login> {
            Login(navController)
        }
        composable<NavRoutes.home> {
            HomeScreen(navController)
        }
        composable<NavRoutes.topbar> {
            TopAppBar(navController)
        }

    }
}