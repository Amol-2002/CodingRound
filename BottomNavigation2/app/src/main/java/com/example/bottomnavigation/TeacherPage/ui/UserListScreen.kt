package com.example.bottomnavigation.TeacherPage.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bottomnavigation.TeacherPage.viewmodel.UserViewModel

@Composable
fun UserListScreen(viewModel: UserViewModel = viewModel()) {

    val users = viewModel.userList.value

    LazyColumn {

        items(users) { user ->

            UserCard(user)

        }

    }

}