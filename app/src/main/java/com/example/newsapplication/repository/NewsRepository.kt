package com.example.newsapplication.repository

import androidx.lifecycle.LiveData
import com.example.newsapplication.api.NewsAPI
import com.example.newsapplication.db.NewsDao
import com.example.newsapplication.model.Article
import com.example.newsapplication.model.NewsModel
import com.example.newsapplication.model.RoomModel
import com.example.newsapplication.utilities.Constants.Companion.API_KEY
import com.example.newsapplication.utilities.Resource
import retrofit2.Response
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped
class NewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    private val api : NewsAPI
){

    //for retrofit
    suspend fun getNewsList(): Resource<Response<NewsModel>>{
        val response = try{
            api.getAllNews("us",1,API_KEY)
        }catch (e:Exception){
            return Resource.Error("NewsRepository-getNewsList doesn't work.")
        }

        return Resource.Success(response)
    }

    suspend fun getNewsRefreshList(currentPage: Int, pageSize: Int): Resource<Response<NewsModel>> {
        val nextPage = currentPage + 1 // Calculate the next page number to fetch

        val response = try {
            api.getAllNews("us", nextPage, API_KEY) // Fetch the next page of news
        } catch (e: Exception) {
            return Resource.Error("NewsRepository-getNewsRefreshList doesn't work.")
        }

        return Resource.Success(response)
    }

    suspend fun getSearchNews(searchText : String): Resource<Response<NewsModel>>{

        val response = try {
            api.getSearchNews(searchText,1, API_KEY)
        }catch (e:Exception){
            return Resource.Error("NewsRepository-getSearchNews doesn't work.")
        }
        return Resource.Success(response)
    }


    suspend fun getOneNews(newsContentQuery : String): Resource<NewsModel> {
        val response = try {
            api.getOneNews(newsContentQuery,1, API_KEY)
        }catch (e:Exception){
            return Resource.Error("NewsRepository-getOneNews doesn't work.")
        }
        return  Resource.Success(response)
    }

    //for Room

    suspend fun insertNews(news: RoomModel){
        newsDao.insertNews(news)
    }

    suspend fun deleteNews(news: RoomModel){
        newsDao.deleteArt(news)
    }

    fun getNews(): LiveData<List<RoomModel>> {
        return newsDao.observeNews()
    }



}