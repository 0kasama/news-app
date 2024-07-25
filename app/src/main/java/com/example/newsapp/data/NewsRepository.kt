package com.example.newsapp.data

import com.example.newsapp.data.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class NewsRepository {
    private val newsApi = NewsService.newsApi

    suspend fun getTopHeadlines(category: String = "business", page: Int = 1): News =
        withContext(Dispatchers.IO) {
            val response = newsApi.getHeadlines(category, page).await()
            response
        }

    suspend fun getAllNews(q: String = "economy", page: Int = 1): News =
        withContext(Dispatchers.IO) {
            val response = newsApi.getAllNews(q, page).await()
            response
        }
}
