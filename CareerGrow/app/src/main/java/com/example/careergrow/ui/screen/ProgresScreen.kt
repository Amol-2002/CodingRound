package com.example.careergrow.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.careergrow.navigation.NavRoutes


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ProgresScreen(navController: NavHostController) {

    var topExpanded by remember { mutableStateOf(false) }


    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Back To Login Screen") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF46ACFE)
            ),


            actions = {

                IconButton(onClick = {


                        val context = navController.context
                        val sharedPref =
                            context.getSharedPreferences("app_pref", Context.MODE_PRIVATE)

                        sharedPref.edit().clear().apply() // 🔥 MOST IMPORTANT

                        navController.navigate(NavRoutes.LoginScreen) {
                            popUpTo(0) // 🔥 clear backstack
                        }




                }) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Menu"
                    )
                }

            }
        )

    }, bottomBar = {}) { innerpadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Your Profile Is In Process Please Wait")
        }
    }
}