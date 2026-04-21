package com.example.careergrow.ui.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.careergrow.R
import com.example.careergrow.navigation.NavRoutes
import kotlinx.coroutines.delay


@Composable
fun SplashScreenUI(
    navController: NavHostController) {

    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("app_pref", Context.MODE_PRIVATE)

    LaunchedEffect(true) {
        delay(2000)

        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

        val userStatus = sharedPref.getString("user_status", "")

        if (isLoggedIn) {

            when (userStatus) {

                "pending" -> {
                    navController.navigate(NavRoutes.CProfileScreen("")) {
                        popUpTo(NavRoutes.SplashScreen) { inclusive = true }
                    }
                }

                "inprogress" -> {
                    navController.navigate(NavRoutes.Progress) {
                        popUpTo(NavRoutes.SplashScreen) { inclusive = true }
                    }
                }

                "approved" -> {
                    navController.navigate(NavRoutes.MainScreen) {
                        popUpTo(NavRoutes.SplashScreen) { inclusive = true }
                    }
                }

                else -> {
                    navController.navigate(NavRoutes.LoginScreen) {
                        popUpTo(NavRoutes.SplashScreen) { inclusive = true }
                    }
                }
            }

        } else {
            navController.navigate(NavRoutes.LoginScreen) {
                popUpTo(NavRoutes.SplashScreen) { inclusive = true }
            }
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.trifrnd),
            contentDescription = "Splash Image",
            modifier = Modifier.size(90.dp)
        )

        Text(text = "From Trifrnd",
            modifier = Modifier, fontSize = 22.sp,
            textAlign = TextAlign.Center)
    }

}