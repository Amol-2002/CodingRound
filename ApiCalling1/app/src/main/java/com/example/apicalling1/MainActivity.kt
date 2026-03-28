package com.example.apicalling1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.apicalling1.navigation.NavGraph
import com.example.apicalling1.navigation.NavRoutes
import com.example.apicalling1.screens.HomeScreen
import com.example.apicalling1.ui.theme.ApiCalling1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApiCalling1Theme {
                NavGraph()
            }
        }
    }
}
