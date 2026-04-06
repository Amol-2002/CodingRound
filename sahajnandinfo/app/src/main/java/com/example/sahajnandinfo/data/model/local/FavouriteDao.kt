package com.example.sahajnandinfo.data.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sahajnandinfo.data.model.local.FavouriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fav: FavouriteEntity)

    @Query("SELECT * FROM favourites")
    fun getAll(): Flow<List<FavouriteEntity>>
}