package com.example.careergrow.ui.navScreen

import android.R
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.careergrow.navigation.NavRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(parentNavController: NavHostController) {
    val context = LocalContext.current
    var Expanded by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Career Grow" ) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF46ACFE)
                ),
                navigationIcon = {
                    IconButton(onClick = {Expanded = true}) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                    DropdownMenu(
                        expanded = Expanded,
                        onDismissRequest = {Expanded = false }

                    ) {
                        DropdownMenuItem(
                            text = { Text("Logout") },
                            onClick = {


                                val sharedPref = context.getSharedPreferences("app_pref", Context.MODE_PRIVATE)

                                sharedPref.edit().clear().apply()

                                parentNavController.navigate(NavRoutes.LoginScreen) {
                                    popUpTo(NavRoutes.MainScreen) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                },
                actions ={
                    IconButton(onClick = {}){
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Scan"
                        )
                    }
                    IconButton(onClick = {}){
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "send"
                        )
                    }
                }
            )

        },
        bottomBar = {}
    )
    { innerPadding ->
        Column (
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Home Screen")
        }
    }
}