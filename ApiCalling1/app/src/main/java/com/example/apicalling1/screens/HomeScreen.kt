package com.example.apicalling1.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.apicalling1.api.RetrofitInstance
import com.example.apicalling1.model.User
import com.example.apicalling1.screens.componants.TopAppBar

@Composable
fun HomeScreen(navController: NavHostController) {

    val apiService = remember { RetrofitInstance.getApiService() }
    var posts by remember { mutableStateOf<List<User>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            val response = apiService.getposts()
            posts = response.users
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    Scaffold(topBar = { TopAppBar(navController) }, bottomBar = {}) { innerPadding ->

        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(posts) {post ->
                Card(modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .fillMaxSize()) {
                    Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically){
                        AsyncImage(
                            model = post.image,
                            contentDescription = "user",
                            modifier = Modifier.size(70.dp)
                        )

                        Column (modifier = Modifier.padding(8.dp)){
                            Text(post.id.toString())
                            Text("${post.firstName} ${post.maidenName} ${post.lastName}")
                            Text(post.birthDate)
                        }
                    }
            }
            }
        }
    }
}