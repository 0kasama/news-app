package com.example.newsapp.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.newsapp.data.model.NewsArticle
import com.example.newsapp.ui.component.HeadlineItem
import com.example.newsapp.ui.component.NewsItem
import com.example.newsapp.viewmodel.NewsViewModel

@Composable
fun HomeScreen(
    viewModel: NewsViewModel,
    onHeadlineClick: (String) -> Unit,
    onNewsItemClick: (NewsArticle) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            Text(
                text = "Top Headlines",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primary)
        }
        item {
            LazyRow {
                itemsIndexed(viewModel.headlines.value) { _, article ->
                    HeadlineItem(
                        article = article,
                        onItemClick = { onHeadlineClick(article.url) }
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            Text(
                text = "Latest News",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primary)
        }
        itemsIndexed(viewModel.allNews.value) { _, article ->
            NewsItem(
                article = article,
                onItemClick = { onNewsItemClick(article) }
            )
            HorizontalDivider()
        }
    }
}