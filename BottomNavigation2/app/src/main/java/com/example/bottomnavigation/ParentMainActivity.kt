package com.example.bottomnavigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigation.ParentPage.AppTopBarParent
import com.example.bottomnavigation.TeacherPage.MyNavBar
import com.example.bottomnavigation.TeacherPage.navigation.NavBarNavigation
@Composable
fun ParentMainActivity(navController: NavHostController) {

    val bottomNavController = rememberNavController()

    Scaffold(
        topBar = { AppTopBarParent() },
        bottomBar = { MyNavBar(navController = bottomNavController, ) }
    ) { innerPadding ->

        Box(modifier = Modifier.padding(innerPadding)) {
            NavBarNavigation(navController = bottomNavController)
        }

    }
}