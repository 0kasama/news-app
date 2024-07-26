package com.example.newsapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.NewsRepository
import com.example.newsapp.data.model.NewsArticle
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _headlines = mutableStateOf<List<NewsArticle>>(emptyList())
    val headlines: State<List<NewsArticle>> = _headlines

    private val _allNews = mutableStateOf<List<NewsArticle>>(emptyList())
    val allNews: State<List<NewsArticle>> = _allNews

    init {
        fetchHeadlines()
        fetchAllNews()
    }

    private fun fetchHeadlines() {
        viewModelScope.launch {
            try {
                val result = repository.getTopHeadlines()
                _headlines.value = result.articles
            } catch (e: Exception) {
                println("Error fetching headlines: ${e.message}")
            }
        }
    }

    private fun fetchAllNews() {
        viewModelScope.launch {
            try {
                val result = repository.getAllNews()
                _allNews.value = result.articles
            } catch (e: Exception) {
                println("Error fetching all news: ${e.message}")
            }
        }
    }

    fun getArticleByUrl(url: String?): NewsArticle? {
        return allNews.value.find { it.url == url }
    }
}