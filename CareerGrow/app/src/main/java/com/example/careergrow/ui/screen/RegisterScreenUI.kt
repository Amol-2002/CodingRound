package com.example.careergrow.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.careergrow.R
import com.example.careergrow.navigation.NavRoutes
import com.example.careergrow.ui.viewmodel.RegisterViewModel

@Composable
fun RegisterScreenUI(navController: NavHostController) {
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val viewModel: RegisterViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var mobileError by remember { mutableStateOf("") }
    Scaffold(
        bottomBar = {}
    ) { innerpadding ->

        Column {
            Image(
            painter = painterResource(id = R.drawable.group),
            contentDescription = "Splash Image",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
            Spacer(modifier = Modifier.height(5.dp))

            LazyColumn {
                item {


                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .padding(innerpadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.trifrnd),
                            contentDescription = "Splash Image",
                            modifier = Modifier.size(90.dp)
                        )


                        Spacer(modifier = Modifier.height(15.dp))

                        Text("Register Now..!", Modifier, fontWeight = FontWeight.Bold, fontSize = 25.sp)

                        Spacer(modifier = Modifier.height(25.dp))


                        OutlinedTextField(
                            value = firstname,
                            onValueChange = {     if (it.length <= 12) {
                                firstname = it
                            } },
                            label = { Text("First Name") },
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

                        OutlinedTextField(
                            value = lastname,
                            onValueChange = {     if (it.length <= 12) {
                                lastname = it
                            } },
                            label = { Text("Last Name") },
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
                        OutlinedTextField(
                            value = mobile,
                            onValueChange = {
                                if (it.length <= 10 && it.all { ch -> ch.isDigit() }) {
                                    mobile = it
                                    mobileError =   ""
                                }
                                            },
                            label = { Text("Mobile Number") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            singleLine = true,
                            leadingIcon = {
                                Icon(Icons.Default.Phone, contentDescription = null)
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

                        OutlinedTextField(
                            value = email,
                            onValueChange = {
                                email = it },
                            label = { Text("Email ") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            singleLine = true,
                            leadingIcon = {
                                Icon(Icons.Default.Email, contentDescription = null)
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
                        Spacer(modifier = Modifier.height(40.dp))

                        Button(
                            onClick = {

                                when {

                                    firstname.isBlank() -> {
                                        viewModel.errorMessage = "Enter first name"
                                    }

                                    firstname.length < 3 -> {
                                        viewModel.errorMessage = "First name must be at least 3 characters"
                                    }

                                    lastname.isBlank() -> {
                                        viewModel.errorMessage = "Enter last name"
                                    }

                                    mobile.isBlank() -> {
                                        viewModel.errorMessage = "Enter mobile number"
                                    }

                                    mobile.length != 10 -> {
                                        viewModel.errorMessage = "Mobile must be 10 digits"
                                    }

                                    !mobile.all { it.isDigit() } -> {
                                        viewModel.errorMessage = "Mobile must contain only numbers"
                                    }

                                    email.isBlank() -> {
                                        viewModel.errorMessage = "Enter email"
                                    }

                                    !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                                        viewModel.errorMessage = "Enter valid email"
                                    }

                                    password.isBlank() -> {
                                        viewModel.errorMessage = "Enter password"
                                    }

                                    password.length < 6 -> {
                                        viewModel.errorMessage = "Password must be at least 6 characters"
                                    }

                                    else -> {
                                        viewModel.errorMessage = "" // clear error
                                        viewModel.register(
                                            firstname,
                                            lastname,
                                            mobile,
                                            password,
                                            email
                                        )
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(53.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFF9A86),
                                contentColor = Color.Black
                            ),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text("Register", fontSize = 20.sp)
                        }
                        if (viewModel.errorMessage.isNotEmpty()) {
                            Text(
                                text = viewModel.errorMessage,
                                color = Color.Red
                            )
                        }

                        val context = navController.context

                        LaunchedEffect(viewModel.registerSuccess) {
                            if (viewModel.registerSuccess) {

                                Toast.makeText(
                                    context,
                                    "Registration Successful",
                                    Toast.LENGTH_SHORT
                                ).show()

                                navController.navigate(NavRoutes.LoginScreen) {
                                    popUpTo(NavRoutes.RegisterScreen) { inclusive = true }
                                }
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            Arrangement.Center,
                            Alignment.CenterVertically
                        ) {
                            Text(
                                "Already Register?", fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                "Login Here?", modifier = Modifier.clickable {
                                    navController.navigate(
                                        NavRoutes.LoginScreen
                                    )
                                }, Color(0xFFFF9A86)
                            )
                        }
                    }
                }
            }
        }
    }
}