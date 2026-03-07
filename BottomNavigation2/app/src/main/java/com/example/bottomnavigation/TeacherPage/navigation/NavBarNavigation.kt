package com.example.bottomnavigation.TeacherPage.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bottomnavigation.TeacherPage.navScreens.NavBarHomeScreen
import com.example.bottomnavigation.TeacherPage.navScreens.NavBarNotificationScreen
import com.example.bottomnavigation.TeacherPage.navScreens.NavBarProfileScreen
import com.example.bottomnavigation.TeacherPage.navScreens.NavBarSearchScreen
@Composable
fun NavBarNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavBarRoutes.Home
    ) {

        composable<NavBarRoutes.Home> {
            NavBarHomeScreen(navController)
        }

        composable<NavBarRoutes.Search> {
            NavBarSearchScreen(navController)
        }

        composable<NavBarRoutes.Notification> {
            NavBarNotificationScreen(navController)
        }

        composable<NavBarRoutes.Profile> {
            NavBarProfileScreen(navController)
        }
    }
}