package com.example.newsapplication.api


import com.example.newsapplication.model.NewsModel
import com.example.newsapplication.utilities.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    //https://newsapi.org/v2/everything?q=Apple&from=2023-03-25&sortBy=popularity&apiKey=d78074c607ce4e3dbcac74b29a07df19
    //https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY
    //https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY

    //for ListScreen
    @GET("v2/top-headlines")
    suspend fun getAllNews(
        @Query("country")
        country: String = "us",
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ):Response<NewsModel>


    //for SearchScreen
    @GET("v2/everything")
    suspend fun getSearchNews(
        @Query("q")
        searchQuery: String ,
        @Query("page")
        pageNumber : Int = 1,
        @Query("apiKey")
        apiKEy: String = API_KEY
    ): Response<NewsModel>

    @GET("v2/everything")
    suspend fun getOneNews(
        @Query("q")
        newsContentQuery: String ,
        @Query("page")
        pageNumber : Int = 1,
        @Query("apiKey")
        apiKEy: String = API_KEY
    ): NewsModel

}