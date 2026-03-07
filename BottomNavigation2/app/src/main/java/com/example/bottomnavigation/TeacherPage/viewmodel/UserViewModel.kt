package com.example.bottomnavigation.TeacherPage.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigation.TeacherPage.model.User
import com.example.bottomnavigation.TeacherPage.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    var userList = mutableStateOf<List<User>>(emptyList())

    private val repository = UserRepository()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {

        viewModelScope.launch {

            userList.value = repository.getUsers()

        }

    }

}
