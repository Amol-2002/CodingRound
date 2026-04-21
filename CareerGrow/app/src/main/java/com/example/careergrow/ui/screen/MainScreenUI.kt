package com.example.careergrow.ui.screen

import android.R.attr.title
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.careergrow.navigation.BottomNavGraph
import com.example.careergrow.ui.componant.NavBar

@Composable
fun MainScreenUI(NavController: NavHostController) {
val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavBar(bottomNavController)
        }
    ) { innerPadding ->

        BottomNavGraph(navController = bottomNavController,
            parentNavController = NavController)
    }
}