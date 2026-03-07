package com.example.bottomnavigation.TeacherPage

import android.R.attr.contentDescription
import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarTeacher(navController: NavHostController) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = "Teacher Page",
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        actions = {
            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search"
                )
            }
            IconButton(onClick = {expanded = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Morevert"
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                containerColor =  Color.White
            ) {
                DropdownMenuItem(text = { Text("Setting") },
                    leadingIcon = {
                        Icon(Icons.Outlined.Settings,
                        contentDescription = "Settings")  },
                    onClick = {expanded = false })

                DropdownMenuItem(text = { Text("Report") },
                    leadingIcon = {
                        Icon(Icons.Outlined.Warning,
                            contentDescription = "Settings")  },
                    onClick = {expanded = false })

                DropdownMenuItem(text = { Text("Change Password") },
                    leadingIcon = {
                        Icon(Icons.Outlined.Edit,
                            contentDescription = "PAssword")  },
                    onClick = { expanded = false })

                DropdownMenuItem(
                    text = { Text("Log Out") },
                    leadingIcon = {
                        Icon(Icons.Outlined.ExitToApp, contentDescription = "logout")
                                  },
                    onClick = {expanded = false
                        val sharedPref = context.getSharedPreferences("login", Context.MODE_PRIVATE)

                        sharedPref.edit()
                            .clear()
                            .apply()

                        navController.navigate("login") {
                            popUpTo("teacher") { inclusive = true }
                        }
                    })

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor =  Color.Black,
            navigationIconContentColor = Color.Black,
            actionIconContentColor = Color.Black
        )
    )
}