package com.example.sahajnandinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sahajnandinfo.navigation.NavGraph
import com.example.sahajnandinfo.ui.theme.theme.SahajnandinfoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SahajnandinfoTheme {
              NavGraph()
            }
        }
    }
}
