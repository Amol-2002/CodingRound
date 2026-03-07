package com.example.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigation.screens.LoginScreen
import com.example.bottomnavigation.screens.SplashScreen
@Composable
fun MainNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        composable("splash") {
            SplashScreen(navController)
        }

        composable("login") {
            LoginScreen(navController)
        }

        composable("teacher") {
            TeacherMainActivity(navController)
        }

        composable("parent") {
            ParentMainActivity(navController)
        }





    }
}

@Composable
fun TopAppBarrTeacher(navController: NavHostController) {
    TODO("Not yet implemented")
}