package com.example.careergrow.ui.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.careergrow.R
import com.example.careergrow.navigation.NavRoutes
import com.example.careergrow.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreenUI(navController: NavHostController) {
    val viewModel: LoginViewModel = viewModel()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(

    ){
        Image(
            painter = painterResource(id = R.drawable.group),
            contentDescription = "Splash Image",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

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


        Spacer(modifier = Modifier.height(25.dp))


        Text("Welcome Back..!", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = username,
            onValueChange = {
                if (it.length <= 12) {
                    username = it
                }
            },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
              singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = null)
            },

            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,

                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray,

                focusedIndicatorColor = Color(0xFFFF9A86),
                unfocusedIndicatorColor = Color.Gray,    // ✅ correct

                focusedLabelColor = Color(0xFFFF9A86),
                unfocusedLabelColor = Color.Gray,

                cursorColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,

                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray,

                focusedIndicatorColor = Color(0xFFFF9A86),     // ✅ correct
                unfocusedIndicatorColor = Color.Gray,


                focusedLabelColor = Color(0xFFFF9A86),
                unfocusedLabelColor = Color.Gray,
                cursorColor = Color.Black

            ),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = null)
            },
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle Password"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Forgot Password?",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            color=Color(0xFFFF9A86)
        )


        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {




                if (username.isEmpty() || password.isEmpty()) {
                    viewModel.errorMessage = "Enter all fields"
                } else {
                    viewModel.login(username, password)
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(53.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF9A86)
                // 0xFF46ACFE blue
                ,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Login Here", fontSize = 20.sp)
        }

        if (viewModel.errorMessage.isNotEmpty()) {
            Text(
                text = viewModel.errorMessage,
                color = Color.Red
            )
        }
        LaunchedEffect(viewModel.loginSuccess) {
            if (viewModel.loginSuccess) {

                val context = navController.context
                val sharedPref = context.getSharedPreferences("app_pref", Context.MODE_PRIVATE)

                sharedPref.edit()
                    .putBoolean("isLoggedIn", true)
                    .putString("user_status", viewModel.userStatus) // 🔥 important
                    .apply()

                when (viewModel.userStatus) {

                    "Pending" -> {
                        navController.navigate(NavRoutes.MainScreen) {
                            popUpTo(NavRoutes.LoginScreen) { inclusive = true }
                        }
                    }

                    "In Process" -> {
                        navController.navigate(NavRoutes.MainScreen) {
                            popUpTo(NavRoutes.LoginScreen) { inclusive = true }
                        }
                    }

                    "approved" -> {
                        navController.navigate(NavRoutes.MainScreen) {
                            popUpTo(NavRoutes.LoginScreen) { inclusive = true }
                        }
                    }

                    else -> {
                        viewModel.errorMessage = "Unknown status"
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.Center,
            Alignment.Bottom
        ) {
            Text(
                "Don't have Account?", fontWeight = FontWeight.SemiBold
            )
            Text(
                "Register Here!", modifier = Modifier.clickable {
                    navController.navigate(
                        NavRoutes.RegisterScreen
                    ) {
                        popUpTo(NavRoutes.LoginScreen) {
                            inclusive = true
                        }
                    }
                },Color(0xFFFF9A86)
            )
        }
    }

}
}