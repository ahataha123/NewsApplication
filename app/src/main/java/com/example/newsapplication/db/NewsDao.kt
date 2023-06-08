package com.example.newsapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapplication.model.RoomModel

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: RoomModel)

    @Delete
    suspend fun deleteArt(news:RoomModel)

    @Query("SELECT * FROM savedNews")
    fun observeNews(): LiveData<List<RoomModel>>




}