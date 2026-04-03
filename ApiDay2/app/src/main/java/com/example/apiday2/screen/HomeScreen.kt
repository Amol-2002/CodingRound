package com.example.apiday2.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.apiday2.Api.RetrofitInstance
import com.example.apiday2.componants.TopAppbarr
import com.example.apiday2.model.UserResponseItem

@Composable

fun HomeScreen(parentNavController: NavHostController) {
    val apiService = remember { RetrofitInstance.getApiService() }
    var posts by remember { mutableStateOf<List<UserResponseItem>>(emptyList()) }

    LaunchedEffect(Unit) {

        try {
            val response = apiService.getUsers()
            posts = response
        } catch (
            e: Exception
        ) {
            e.printStackTrace()
        }
    }
    Scaffold(topBar = { TopAppbarr(parentNavController) }, bottomBar = { }) { innerpadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)

        ) {
            items(posts){post ->

                Card (modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).fillMaxWidth()){
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row {
                            Text("Candidate Name : ")
                            Text(text = post.name)
                        }
                        Row {
                            Text("Email : ")
                            Text(text = post.email)
                        }
                        Row {
                            Text("City : ")
                            Text(text = post.address.toString())
                        }
                        Row {
                            Text("Phone No. : ")
                            Text(text = post.phone)
                        }
                    }
                }
            }
        }
    }
}
