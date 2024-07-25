package com.example.newsapp.data

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://newsapi.org/"

interface NewsApi {
    @GET("v2/top-headlines?country=us&pageSize=10")
    fun getHeadlines(
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Call<News>

    @GET("v2/everything?sortBy=publishedAt&language=en&pageSize=50")
    fun getAllNews(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Call<News>
}
