package com.example.apiday2.componants

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.apiday2.navigation.bottomnav.BottomRoutes

@Composable
fun MyNavBar(navController: NavHostController ){

    val navItem = listOf(
        NavItem("Home", icon = Icons.Outlined.Home, selectedIcon = Icons.Filled.Home, routes = BottomRoutes.Home),
        NavItem("Message", icon = Icons.Outlined.MailOutline, selectedIcon = Icons.Filled.Email, routes = BottomRoutes.Message),
        NavItem("Notifications", icon = Icons.Outlined.NotificationsNone, selectedIcon = Icons.Filled.Notifications, routes = BottomRoutes.Notification),
        NavItem("Profile", icon = Icons.Outlined.Person, selectedIcon = Icons.Filled.Person, routes = BottomRoutes.Profile
        )
    )

    val backStackEntry = navController.currentBackStackEntryAsState()
 val currentRoute = backStackEntry.value?.destination?.route

    NavigationBar() {

navItem.forEach { item ->

    NavigationBarItem(selected = currentRoute == item.routes::class.qualifiedName,
//        selected = item.title==key,
        onClick = {
            navController.navigate(item.routes){
                popUpTo(navController.graph.startDestinationId){
                    saveState  = true
                }
                launchSingleTop = true
            }
        },
        icon = {
            Icon(imageVector = item.icon,
                contentDescription = "Home")
        },
        label = { Text(item.title) }
    )
}

    }
}
data class NavItem(
    val title: String,
    val icon: ImageVector,
    val selectedIcon : ImageVector,
    val routes : BottomRoutes
)