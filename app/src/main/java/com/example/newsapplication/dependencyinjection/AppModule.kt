package com.example.newsapplication.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.example.newsapplication.api.NewsAPI
import com.example.newsapplication.db.NewsDao
import com.example.newsapplication.db.NewsDatabase
import com.example.newsapplication.repository.NewsRepository
import com.example.newsapplication.utilities.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //In this way, it can be easily injected into the view model.
    @Singleton
    @Provides
    fun provideNewsRepository(
        newsDao: NewsDao,
        api: NewsAPI,
    )= NewsRepository(newsDao,api)

    @Singleton
    @Provides
    fun provideNewsApi(): NewsAPI{
        val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()


        }
        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }
        return api
    }

    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context)= Room.databaseBuilder(
        context, NewsDatabase::class.java,"newsdb"
        ).build()

   @Singleton
   @Provides
   fun injectDao(database: NewsDatabase) = database.newsDao()


}