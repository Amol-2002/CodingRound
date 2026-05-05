package com.example.twinr.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.twinr.data.Contact
import com.example.twinr.isInternetAvailable
import com.example.twinr.viewmodel.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun AddContactScreen(navController: NavHostController, viewModel: ContactViewModel) {
    val context = LocalContext.current
    var number by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Add Contact")
                },
                actions = {

                    IconButton(onClick = {navController.navigate("list")}) {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "list"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            OutlinedTextField(
                value = number,
                onValueChange = { number = it },
                label = { Text("Number") },

                )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },

                )

            OutlinedTextField(
                value = mobile,
                onValueChange = { mobile = it },
                label = { Text("Mobile") },

                )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {

                    if (isInternetAvailable(context)) {

                        viewModel.insert(
                            Contact(
                                number = number,
                                name = name,
                                mobile = mobile
                            )
                        )

                        Toast.makeText(context, "Data stored successfully", Toast.LENGTH_SHORT).show()

                        number = ""
                        name = ""
                        mobile = ""

                    } else {

                        Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()

                    }

                }
            ) {
                Text("Add")
            }
        }
    }
}

