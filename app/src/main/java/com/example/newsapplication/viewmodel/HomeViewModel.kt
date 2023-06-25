package com.example.newsapplication.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.model.Article
import com.example.newsapplication.repository.NewsRepository
import com.example.newsapplication.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository : NewsRepository
) : ViewModel() {

    var newsList = mutableStateOf<List<Article>>(listOf())
    private var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    private var currentPage = 1 // Current page value for pagination
    private val pageSize = 20 // Number of items per page

    init {
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = repository.getNewsList()) {
                is Resource.Success -> {

                    val news = result.data!!.body().apply {
                        this!!.articles
                    }
                    errorMessage.value = ""
                    isLoading.value = false
                    newsList.value = news!!.articles

                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }

                else -> {
                    println("Error loading News")
                }
            }

        }

    }

    fun refreshNews() {
        viewModelScope.launch {
            isLoading.value = true

            val refreshedPage = 1 // Start with the first page for refreshing

            when (val result = repository.getNewsRefreshList(refreshedPage)) {
                is Resource.Success -> {
                    val news = result.data!!.body().apply {
                        this!!.articles
                    }
                    errorMessage.value = ""
                    isLoading.value = false
                    newsList.value = news!!.articles.shuffled() // Shuffle the articles randomly
                    currentPage = refreshedPage // Update the current page value
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
                else -> {
                    println("Error refreshing News")
                }
            }
        }
    }
}


