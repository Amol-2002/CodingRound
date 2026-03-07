package com.example.bottomnavigation.TeacherPage

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bottomnavigation.TeacherPage.navigation.NavBarRoutes
@Composable
fun MyNavBar(navController: NavHostController) {

    val navItem = listOf(
        NavItem("Home", Icons.Default.Home, NavBarRoutes.Home),
        NavItem("Search", Icons.Default.Search, NavBarRoutes.Search),
        NavItem("Profile", Icons.Default.Person, NavBarRoutes.Profile),
        NavItem("Notification", Icons.Default.Notifications, NavBarRoutes.Notification)
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination

    NavigationBar {

        navItem.forEach { item ->

            NavigationBarItem(

                selected = currentDestination?.route == item.routes::class.qualifiedName,

                onClick = {
                    navController.navigate(item.routes) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },

                label = { Text(item.title) },

                alwaysShowLabel = true,

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    selectedTextColor = Color.Blue,
                    indicatorColor = Color.Blue.copy(alpha = 0.1f),
                    unselectedTextColor = Color.Black,
                    unselectedIconColor = Color.Black
                )
            )
        }
    }
}

data class NavItem(
    val title: String,
    val icon: ImageVector,
    val routes: NavBarRoutes
)
