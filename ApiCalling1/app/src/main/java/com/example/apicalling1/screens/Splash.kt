package com.example.apicalling1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.apicalling1.R
import com.example.apicalling1.navigation.NavRoutes
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavHostController) {

    LaunchedEffect(Unit) {
        delay(2000) // 2 seconds delay
        navController.navigate(NavRoutes.login) {
            popUpTo(NavRoutes.splash) { inclusive = true }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            imageVector = (Icons.Default.Face),
            contentDescription = "Splash Screen", modifier = Modifier.size(50.dp)
        )
        Text(text = "Twinr",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Green)
    }

}