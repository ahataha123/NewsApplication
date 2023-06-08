package com.example.newsapplication.viewmodel



import androidx.lifecycle.ViewModel
import com.example.newsapplication.model.NewsModel
import com.example.newsapplication.repository.NewsRepository
import com.example.newsapplication.utilities.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OneNewsViewModel @Inject constructor(
    private val repository: NewsRepository):ViewModel() {

        suspend fun getOneNews(newsContentQuery: String): Resource<NewsModel> {
            return repository.getOneNews(newsContentQuery)
        }
}


