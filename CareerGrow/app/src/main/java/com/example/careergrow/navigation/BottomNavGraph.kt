package com.example.careergrow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.careergrow.ui.navScreen.HomeScreenUI
import com.example.careergrow.ui.navScreen.MessageScreenUi
import com.example.careergrow.ui.navScreen.NotificationsScreenUi
import com.example.careergrow.ui.navScreen.ProfileScreenUI

@Composable
fun BottomNavGraph(navController: NavHostController,
                   parentNavController: NavHostController) {


    NavHost(
        navController = navController,
        startDestination = BottomNavRoutes.HomeScreen
    ){
        composable<BottomNavRoutes.HomeScreen> {
            HomeScreenUI(parentNavController)
        }
        composable<BottomNavRoutes.MessageScreen> {
            MessageScreenUi(parentNavController)
        }
        composable<BottomNavRoutes.NotificationScreen> {
            NotificationsScreenUi(parentNavController)
        }
        composable<BottomNavRoutes.ProfileScreen> {
            ProfileScreenUI(parentNavController)
        }

    }

}