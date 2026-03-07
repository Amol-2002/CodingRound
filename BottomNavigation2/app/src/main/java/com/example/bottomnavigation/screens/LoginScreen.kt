package com.example.bottomnavigation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bottomnavigation.R
import com.example.bottomnavigation.componants.RoleSwitch

@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    var rememberMe by remember { mutableStateOf(false) }
    var role by remember { mutableStateOf("teachermain") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Image(
                painter = painterResource(id = R.drawable.user), contentDescription = "Logo", modifier = Modifier.size(90.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(text = "Welcome Back", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Login Here", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(30.dp))

            RoleSwitch(

                selectedRole = role,
                onRoleChange = { role = it }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Enter Email",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "ID - Teacher") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(

//                    focusedTextColor = Color.Black,
//                    unfocusedTextColor = Color.Gray,

                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.LightGray,

//                    cursorColor = Color.Red,

                    focusedLabelColor = Color.Blue,
                    unfocusedLabelColor = Color.Gray,

//                    focusedContainerColor = Color.White,
//                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Enter Password",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Pass- 123456") },
                modifier = Modifier.fillMaxWidth(), colors = OutlinedTextFieldDefaults.colors(

//                    focusedTextColor = Color.Black,
//                    unfocusedTextColor = Color.Gray,

                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.LightGray,

//                    cursorColor = Color.Red,

                    focusedLabelColor = Color.Blue,
                    unfocusedLabelColor = Color.Gray,

//                    focusedContainerColor = Color.White,
//                    unfocusedContainerColor = Color.White
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Blue,
                        uncheckedColor = Color.Gray
                    )
                )

                Text(
                    text = "Remember Me"
                )

            }
            Text(
                text = "Forgot Password?",
                modifier = Modifier.fillMaxWidth().clickable{},
                textAlign = TextAlign.End, fontWeight = FontWeight.SemiBold, color = Color.Blue
            )

            Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {

                        if (role == "teachermain") {

                            if (email == "Teacher" && password == "123456") {

                                Toast.makeText(context, "Teacher Login Success", Toast.LENGTH_SHORT).show()

                                if (rememberMe) {
                                    val sharedPref = context.getSharedPreferences("login", Context.MODE_PRIVATE)

                                    sharedPref.edit()
                                        .putBoolean("isLoggedIn", true)
                                        .apply()
                                }

                                navController.navigate("teacher") {
                                    popUpTo("login") { inclusive = true }
                                }

                            }

                        }

                        else if (role == "parentmain") {

                            if (email == "Parent" && password == "123456") {

                                Toast.makeText(context, "Parent Login Success", Toast.LENGTH_SHORT).show()

                                navController.navigate("parent")

                            } else {

                                Toast.makeText(context, "Parent Login Failed", Toast.LENGTH_SHORT).show()

                            }

                        }

                    }, modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue, contentColor = Color.White))

            {
                Text(text = "LOG IN",)
            }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(text = "Don't have an account?")
                    Spacer(modifier = Modifier.width(4.dp))

                    Text(text = "Register", fontWeight = FontWeight.SemiBold, color = Color.Blue, modifier = Modifier.clickable{})
                }
            }
        }

