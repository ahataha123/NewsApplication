package com.example.newsapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "savedNews")
data class RoomModel(
    @ColumnInfo(name = "author")
    var author: String,
    @ColumnInfo(name = "content")
    var content: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "publishedAt")
    var publishedAt: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "urlToImage")
    var urlToImage: String,
    @PrimaryKey(autoGenerate = true)
    var id:Int ? = null

)