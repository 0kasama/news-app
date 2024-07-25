package com.example.newsapp.data.model

data class News(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsArticle>
)

data class Source(
    val id: String?,
    val name: String
)

data class NewsArticle(
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    val source: Source
)