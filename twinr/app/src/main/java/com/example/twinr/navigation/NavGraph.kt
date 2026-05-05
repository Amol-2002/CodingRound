package com.example.twinr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.twinr.ui.screen.AddContactScreen
import com.example.twinr.ui.screen.ContactListScreen
import com.example.twinr.viewmodel.ContactViewModel

@Composable
fun AppNavGraph(viewModel: ContactViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "add")
    {

        composable("add") {
            AddContactScreen(navController, viewModel)
        }

        composable("list") {
            ContactListScreen(viewModel)
        }

    }

}