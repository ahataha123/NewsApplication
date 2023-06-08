package com.example.newsapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.model.RoomModel
import com.example.newsapplication.repository.NewsRepository
import com.example.newsapplication.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private val repository: NewsRepository
): ViewModel() {

    val newsList : LiveData<List<RoomModel>>

    init {
        newsList =repository.getNews()
    }


        private var insertNewsMsg = MutableLiveData<Resource<RoomModel>>()
        val insertNewsMessage : LiveData<Resource<RoomModel>>
        get()=insertNewsMsg

    fun resetInsertNewsMsg(){
        insertNewsMsg =MutableLiveData<Resource<RoomModel>>()
    }

    fun deleteNews(news : RoomModel) { viewModelScope.launch{
        repository.deleteNews(news)
        }
    }

    fun insertNews(news: RoomModel){ viewModelScope.launch {
        repository.insertNews(news)
        }
    }

    fun saveNews(author: String, content: String, description: String, publishedAt: String,title: String,url: String,urlToImage: String ){
        val savedNews= RoomModel(author,content, description, publishedAt, title, url, urlToImage)
        insertNews(savedNews)
        insertNewsMsg.postValue(Resource.Success(savedNews))
    }
}