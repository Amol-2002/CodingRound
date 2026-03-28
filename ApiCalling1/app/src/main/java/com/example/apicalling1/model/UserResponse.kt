package com.example.apicalling1.model

data class UserResponse(
    val limit: Int,
    val skip: Int,
    val total: Int,
    val users: List<User>
)