package com.example.apiday2.componants

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.apiday2.navigation.NavRoutes
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppbarr(navController: NavHostController) {
    var menuExpanded by remember { mutableStateOf(false) }
    var moreExpanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text("Welcome") },

        navigationIcon = {
            IconButton(onClick = { menuExpanded = true }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "morv"
                )
            }

        },
        actions = {
            IconButton(onClick = { moreExpanded = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "morevert"
                )
            }
            DropdownMenu(
                expanded = moreExpanded,
                onDismissRequest = { moreExpanded = false }) {
                DropdownMenuItem(
                    text = { Text("logout") }, onClick = {
                        navController.navigate(NavRoutes.Login) {
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    })
            }
        }

    )
}