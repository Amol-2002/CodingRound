package com.example.sahajnandinfo.data.model.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sahajnandinfo.data.local.FavouriteDao

@Database(entities = [FavouriteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao
}