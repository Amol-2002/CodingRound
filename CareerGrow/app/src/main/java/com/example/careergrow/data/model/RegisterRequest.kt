package com.example.careergrow.data.model

data class RegisterRequest(
    val user_fname: String,
    val user_sname: String,
    val user_mobile: String,
    val user_pwd: String,
    val user_email: String
)