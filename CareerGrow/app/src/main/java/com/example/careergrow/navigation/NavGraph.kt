package com.example.careergrow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.careergrow.ui.screen.CompleteProfileScreen
import com.example.careergrow.ui.screen.LoginScreenUI
import com.example.careergrow.ui.screen.MainScreenUI
import com.example.careergrow.ui.screen.ProgresScreen
import com.example.careergrow.ui.screen.RegisterScreenUI
import com.example.careergrow.ui.screen.SplashScreenUI

@Composable
fun NavGraph(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.SplashScreen){

        composable<NavRoutes.SplashScreen>{
            SplashScreenUI(navController)
        }

        composable<NavRoutes.LoginScreen>{
            LoginScreenUI(navController)
        }

        composable<NavRoutes.RegisterScreen> {
            RegisterScreenUI(navController)
        }

        composable<NavRoutes.CProfileScreen> { backStackEntry ->

            val data = backStackEntry.toRoute<NavRoutes.CProfileScreen>()

            CompleteProfileScreen(
                navController = navController,
                mobile = data.mobile // 🔥 pass here
            )
        }

        composable<NavRoutes.Progress> {
            ProgresScreen(navController)
        }

        composable<NavRoutes.MainScreen> {
            MainScreenUI(navController)
        }
    }

}
