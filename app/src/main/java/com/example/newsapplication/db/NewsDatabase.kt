package com.example.newsapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapplication.model.RoomModel

@Database(entities = [RoomModel::class], version = 1)
abstract class NewsDatabase : RoomDatabase(){
    abstract fun newsDao(): NewsDao
}