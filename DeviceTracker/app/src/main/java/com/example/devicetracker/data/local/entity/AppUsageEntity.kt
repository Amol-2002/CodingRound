package com.example.devicetracker.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "app_usage")
data class AppUsageEntity(

    @PrimaryKey
    val packageName: String,

    val appName: String,
    val category: String,
    val usageMinutes: Long,
    val date: Long = System.currentTimeMillis()
)