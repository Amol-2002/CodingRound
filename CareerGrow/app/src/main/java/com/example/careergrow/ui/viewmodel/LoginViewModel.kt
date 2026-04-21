package com.example.careergrow.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careergrow.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = AuthRepository()

    var isLoading by mutableStateOf(false)
    var loginSuccess by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    // 🔥 NEW (IMPORTANT)
    var userStatus by mutableStateOf("")

    fun login(mobile: String, password: String) {

        viewModelScope.launch {
            isLoading = true

            try {
                val response = repository.login(mobile, password)

                if (response.status) {
                    loginSuccess = true

                    // 🔥 STORE USER STATUS
                    userStatus = response.user_status

                } else {
                    errorMessage = response.message
                }

            } catch (e: Exception) {
                errorMessage = "API Error"
            }

            isLoading = false
        }
    }
}