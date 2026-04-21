package com.example.careergrow.ui.componant

import android.R.attr.height
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.careergrow.R
import com.example.careergrow.navigation.BottomNavRoutes

@Composable
fun NavBar(navController: NavHostController) {

    val navItems = listOf(
        NavItem("Home", painterResource(R.drawable.regular_outline_home), BottomNavRoutes.HomeScreen),
        NavItem("Message",painterResource(R.drawable.message), BottomNavRoutes.MessageScreen),
        NavItem("Notification",painterResource(R.drawable.notify), BottomNavRoutes.NotificationScreen),
        NavItem("Profile",painterResource(R.drawable.userprofile), BottomNavRoutes.ProfileScreen)
    )
    val currentBackStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(modifier = Modifier.
    background(Color.White)){


        navItems.forEach { item ->
            val selected = currentBackStackEntry.value?.destination?.route == item.routes::class.qualifiedName

            NavigationBarItem(
                selected = selected,
                onClick = {navController.navigate(item.routes){
                    popUpTo(navController.graph.startDestinationId){
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                          },
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp)
                    )
                },

                label = { Text(item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor =Color(0xFF46ACFE),
                    selectedIconColor = Color(0xFF46ACFE),
                    unselectedTextColor = Color.Black,
                    unselectedIconColor = Color.Black,
                    indicatorColor = Color.Transparent
                )
            )
        }

    }

}

data class NavItem(
    val title : String,
    val icon: Painter,
    val routes : BottomNavRoutes,
)