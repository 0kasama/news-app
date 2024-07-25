package com.example.newsapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsService {
    val newsApi: NewsApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }
}