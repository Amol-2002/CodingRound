package com.example.devicetracker.data.local.model


data class CategoryUsage(
    val category: String,
    val usedMinutes: Long,
    val limitMinutes: Long
)