package com.example.bottomnavigation.TeacherPage.navScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.bottomnavigation.R

@Composable
fun NavBarSearchScreen(navController: NavHostController) {



        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "Search Screen")
//            Image(painter = painterResource(R.drawable.splashimg),
//                contentDescription = "Splash Screen",
//                modifier = Modifier.fillMaxSize(),
//                contentScale = ContentScale.Crop
//            )

    }

}
