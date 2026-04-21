package com.example.careergrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.careergrow.navigation.NavGraph
import com.example.careergrow.ui.screen.CompleteProfileScreen
import com.example.careergrow.ui.theme.CareerGrowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            CareerGrowTheme {
                       NavGraph()

//               CompleteProfileScreen(navController)
            }
        }
    }
}