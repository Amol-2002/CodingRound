package com.example.careergrow.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careergrow.data.repository.AuthRepository
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val repository = AuthRepository()

    var isLoading by mutableStateOf(false)
    var registerSuccess by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    fun register(
        fname: String,
        sname: String,
        mobile: String,
        password: String,
        email: String
    ) {

        viewModelScope.launch {
            isLoading = true

            try {
                val response = repository.register(
                    fname, sname, mobile, password, email
                )

                if (response.status) {
                    registerSuccess = true
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