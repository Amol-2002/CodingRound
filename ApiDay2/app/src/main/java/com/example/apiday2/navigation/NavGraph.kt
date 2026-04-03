package com.example.apiday2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apiday2.screen.LoginScreen
import com.example.apiday2.screen.MainScreen
import com.example.apiday2.screen.SplashScreen

@Composable
fun NavGraph(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash
    ){
        composable<NavRoutes.Splash>{
            SplashScreen(navController)
        }
        composable<NavRoutes.Login>{
            LoginScreen(navController)
        }
        composable <NavRoutes.Main>{
            MainScreen(navController)
        }
    }
}