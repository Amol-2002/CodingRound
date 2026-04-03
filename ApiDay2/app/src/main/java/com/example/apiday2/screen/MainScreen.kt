package com.example.apiday2.screen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.apiday2.componants.MyNavBar
import com.example.apiday2.navigation.bottomnav.BottomGraph

@Composable
fun MainScreen(navController: NavHostController) {
val bottomNavController = rememberNavController()
    Scaffold(topBar = {}, bottomBar = { MyNavBar(bottomNavController)}){
        innerpadding ->
        BottomGraph(navController = bottomNavController,
            parentNavController = navController)

    }
   }