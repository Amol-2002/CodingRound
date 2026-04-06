package com.example.sahajnandinfo.data.model.local


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class FavouriteEntity(
    @PrimaryKey val id: String,
    val name: String,
    val image: String
)