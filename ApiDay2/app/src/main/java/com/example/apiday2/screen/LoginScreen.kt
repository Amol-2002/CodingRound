package com.example.apiday2.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.apiday2.navigation.NavRoutes

@Composable
fun LoginScreen(navController: NavHostController) {

    var username by remember{ mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column (modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        OutlinedTextField(
            value = username,
            onValueChange = {username = it},
            label = { Text("Username")},
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = {
            navController.navigate(NavRoutes.Main){
               popUpTo(NavRoutes.Login){
                   inclusive = true
               }
            }
        }, modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)) {
            Text("Login")
        }
    }
}