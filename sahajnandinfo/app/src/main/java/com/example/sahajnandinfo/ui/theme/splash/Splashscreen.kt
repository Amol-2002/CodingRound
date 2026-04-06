package com.example.sahajnandinfo.ui.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EdgesensorHigh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sahajnandinfo.navigation.NavRoutes
import kotlinx.coroutines.delay

@Composable
fun Splashscreen(navController: NavHostController) {

    LaunchedEffect(Unit) {
        delay(3000)
navController.navigate(NavRoutes.Wallpaper){
    popUpTo(NavRoutes.Splash){
        inclusive = true
    }
}
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Icon(
            imageVector = Icons.Default.EdgesensorHigh,
            contentDescription = "Splashlogo",
            modifier = Modifier.size(90.dp),
            tint = Color.Red
        )
        Text(text = "Welcome to Back..!",color =Color.Red,
            fontWeight = FontWeight.Bold, fontSize = 25.sp)
    }
}